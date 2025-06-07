package com.drugstore.Controller;


import com.drugstore.DTO.CustomerOrderRequestDTO;
import com.drugstore.DTO.CustomerOrderResponseDTO;
import com.drugstore.DTO.DrugResponseDTO;
import com.drugstore.DTO.OrderItemRequestDTO;
import com.drugstore.DTO.ReportFilterDTO;
import com.drugstore.DTO.RevenueReportDTO;
import com.drugstore.Model.CustomerOrder;
import com.drugstore.Model.Drug;
import com.drugstore.Model.OrderItem;
import com.drugstore.Model.OrderStatus;
import com.drugstore.Repository.CustomerOrderRepository;
import com.drugstore.Repository.DrugRepository;
import com.drugstore.Service.DrugService;
import com.drugstore.Service.OrderService;
import com.drugstore.Service.ReportService;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerOrderRepository customerOrderRepository;
   
    @Autowired
    private DrugRepository drugRepository;

    @GetMapping
    public String listOrders(Model model) {
        List<CustomerOrder> orders = customerOrderRepository.findAll();

        for (CustomerOrder order : orders) {
            order.getItems().size();
        }

        model.addAttribute("orders", orders);
        return "order-list"; 
    }


    @GetMapping("/new")
    public String showOrderForm(Model model) {
        CustomerOrderRequestDTO order = new CustomerOrderRequestDTO();
        order.setCustomerName("");

        OrderItemRequestDTO item = new OrderItemRequestDTO();
        order.getItems().add(item);

        model.addAttribute("order", order);
        model.addAttribute("drugs", drugRepository.findAll());

        return "order-form";
    }



    @PostMapping("/save")
    public String saveOrder(@ModelAttribute("order") CustomerOrderRequestDTO order) {
        for (OrderItemRequestDTO item : order.getItems()) {
            Long drugId = item.getDrugId();
            if (drugId == null) {
                throw new IllegalArgumentException("Drug ID must not be null");
            }

            Drug drug = drugRepository.findById(drugId)
                .orElseThrow(() -> new RuntimeException("Drug not found with ID: " + drugId));

            item.setPrice(drug.getPrice());
        }

        orderService.placeOrder(order);
        return "redirect:/orders";
    }


    @PostMapping("/{id}/status")
    public String updateStatus(@PathVariable Long id, @RequestParam("status") OrderStatus status) {
        orderService.updateOrderStatus(id, status);
        return "redirect:/orders";
    }
    @GetMapping("/receipt/{id}")
    public void downloadOrderReceipt(@PathVariable Long id, HttpServletResponse response) throws Exception {
        CustomerOrder order = orderService.getOrderById(id); // fetch order
        List<OrderItem> items = order.getItems();

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=receipt-order-" + id + ".pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

        document.add(new Paragraph("Customer Order Receipt", headerFont));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Customer Name: " + order.getCustomerName(), bodyFont));
        document.add(new Paragraph("Order Date: " + order.getOrderDate(), bodyFont));
        document.add(new Paragraph("Order Status: " + order.getStatus(), bodyFont));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.addCell("Drug Name");
        table.addCell("Quantity");
        table.addCell("Unit Price");
        table.addCell("Total");

        double grandTotal = 0;
        for (OrderItem item : items) {
            table.addCell(item.getDrug().getName());
            table.addCell(String.valueOf(item.getQuantity()));
            table.addCell(String.format("%.2f", item.getPrice()));
            double total = item.getQuantity() * item.getPrice();
            table.addCell(String.format("%.2f", total));
            grandTotal += total;
        }

        document.add(table);
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Grand Total: ₹" + String.format("%.2f", grandTotal), headerFont));
        document.add(new Paragraph("Thanks for visiting...."));
        document.close();
    }
   

}
