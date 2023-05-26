package com.tech.TechShopAPI.controller;

import com.tech.TechShopAPI.config.PaymentConfig;
import com.tech.TechShopAPI.model.Account;
import com.tech.TechShopAPI.model.Bill;
import com.tech.TechShopAPI.payload.request.OrderRequest;
import com.tech.TechShopAPI.payload.response.OrderResponse;
import com.tech.TechShopAPI.payload.response.PaymentResponse;
import com.tech.TechShopAPI.repository.BillRepository;
import com.tech.TechShopAPI.service.AccountService;
import com.tech.TechShopAPI.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    AccountService accountService;
    @Autowired
    OrderService orderService;
    @Autowired
    BillRepository billRepository;

    public static String vnp_apiUrl = "https://sandbox.vnpayment.vn/merchant_webapi/api/transaction";

    @PostMapping
    public ResponseEntity<?> createPayment(@RequestBody OrderRequest requestParams, Principal principal) throws UnsupportedEncodingException {
        OrderResponse orderResponse = orderService.createOrder(requestParams,principal,true);

        int amount = requestParams.getPrice() * 100;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", PaymentConfig.VERSIONVNPAY);
        vnp_Params.put("vnp_Command", PaymentConfig.COMMAND);
        vnp_Params.put("vnp_TmnCode", PaymentConfig.TMNCODE);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", PaymentConfig.CURRCODE);
//        vnp_Params.put("vnp_BankCode", requestParams.getBankCode());
        vnp_Params.put("vnp_TxnRef", String.valueOf(orderResponse.getId()));
        vnp_Params.put("vnp_OrderInfo", PaymentConfig.ORDERINFO);
        vnp_Params.put("vnp_OrderType", PaymentConfig.ORDERTYPE);
        vnp_Params.put("vnp_Locale", PaymentConfig.LOCALDEFAULT);
        vnp_Params.put("vnp_ReturnUrl", PaymentConfig.RETURNURL);
        vnp_Params.put("vnp_IpAddr", PaymentConfig.IPDEFAULT);

//        Date dt = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//        String dateString = format.format(dt);
//
//        String vnp_CreateDate = dateString;
//        vnp_Params.put("vnp_CreateDate",vnp_CreateDate);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());

        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        //Add Params of 2.1.0 Version
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);

        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();

        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {

                //build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));

                // Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));

                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = PaymentConfig.hmacSHA512(PaymentConfig.CHECKSUM, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = PaymentConfig.VNPAYURL + "?" + queryUrl;

        PaymentResponse result = new PaymentResponse();
        result.setStatus("OK");
        result.setMessage("success");
        result.setUrl(paymentUrl);

//        com.google.gson.JsonObject job = new JsonObject();
//        job.addProperty("code", "00");
//        job.addProperty("message", "success");
//        job.addProperty("data", paymentUrl);
//        Gson gson = new Gson();
//        return new ResponseEntity<>(gson.toJson(job),HttpStatus.OK);

        return new ResponseEntity<>(result, HttpStatus.OK);
}
    @GetMapping("/return")
    public String Redirect(
            @RequestParam(value = "vnp_Amount", required = false) String amount,
            @RequestParam(value = "vnp_BankCode", required = false) String bankCode,
            @RequestParam(value = "vnp_BankTranNo", required = false) String bankTranNo,
            @RequestParam(value = "vnp_CardType", required = false) String cardType,
            @RequestParam(value = "vnp_OrderInfo", required = false) String orderInfo,
            @RequestParam(value = "vnp_PayDate", required = false) String payDate,
            @RequestParam(value = "vnp_ResponeCode", required = false) String responseCode,
            @RequestParam(value = "vnp_TmnCode", required = false) String tmnCode,
            @RequestParam(value = "vnp_TransactionNo", required = false) String transactionNo,
            @RequestParam(value = "vnp_TxnRef", required = false) String txnRef,
            @RequestParam(value = "vnp_SecureHashType", required = false) String secureHashType,
            @RequestParam(value = "vnp_SecureHash", required = false) String secureHash
    ,@RequestParam Map<String, String>  params
    ){
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String,String> param : params.entrySet()) {
            sb.append(param.getKey());
            sb.append(" - ");
            sb.append(param.getValue());
            sb.append(" / "+"\t");
        }

        return sb.toString();
    }

    @PutMapping()
    public ResponseEntity<?> createpayment(
        @RequestParam(value = "vnp_Amount", required = false) String amount,
        @RequestParam(value = "vnp_BankCode", required = false) String bankCode,
        @RequestParam(value = "vnp_BankTranNo", required = false) String bankTranNo,
        @RequestParam(value = "vnp_CardType", required = false) String cardType,
        @RequestParam(value = "vnp_OrderInfo", required = false) String orderInfo,
        @RequestParam(value = "vnp_PayDate", required = false) String payDate,
        @RequestParam(value = "vnp_ResponseCode", required = false) String responseCode,
        @RequestParam(value = "vnp_TmnCode", required = false) String tmnCode,
        @RequestParam(value = "vnp_TransactionNo", required = false) String transactionNo,
        @RequestParam(value = "vnp_TxnRef", required = false) String txnRef,
        @RequestParam(value = "vnp_SecureHashType", required = false) String secureHashType,
        @RequestParam(value = "vnp_SecureHash", required = false) String secureHash
        ,Principal principal
    ){
        Account account = accountService.getByEmail(principal.getName());
        if (account == null){
            return new ResponseEntity<>("Tài khoản người dung không hợp lệ",HttpStatus.NOT_ACCEPTABLE);
        }
        Bill bill = orderService.findOrderByAccountIdAndOrderId(account.getId(),Integer.parseInt(txnRef));
        if (bill == null){
            return new ResponseEntity<>("Đơn hàng không tồn tại",HttpStatus.NOT_ACCEPTABLE);
        }

        if (!responseCode.equalsIgnoreCase("00") || bill.getPrice() != (Integer.parseInt(amount)/100)){
            String message = "Giao dịch thất bại";
            orderService.editOrder(bill.getId(), "bị huỷ");
            return new ResponseEntity<>(message,HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(orderService.updatePaidOrder(bill),HttpStatus.OK);
    }
}
