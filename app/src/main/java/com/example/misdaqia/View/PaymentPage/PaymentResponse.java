package com.example.misdaqia.View.PaymentPage;

import java.util.List;

public class PaymentResponse {

    /**
     * id : 8ac7a49f6e44995b016e4500e3613158
     * paymentType : DB
     * paymentBrand : VISA
     * amount : 92.00
     * currency : SAR
     * descriptor : 8006.5868.7396 Misdagia
     * merchantTransactionId : 37
     * result : {"code":"000.200.000","description":"transaction pending"}
     * resultDetails : {"ExtendedDescription":"Transaction pending","AcquirerResponse":"PENDING"}
     * card : {"bin":"420000","last4Digits":"0000","holder":" Abdullah","expiryMonth":"10","expiryYear":"2020"}
     * customer : {"givenName":"Abdullah","surname":"Abdullah","companyName":"misdaqia","email":"abdullah@gmail.com"}
     * redirect : {"url":"https://test.ppipe.net/connectors/migs/redirect.link;jsessionid=F49D6BA232489FC1DBB8A3F1FE1A2BF1.uat01-vm-con03?ndcid=8ac7a4c76d067d4f016d14f7c51b077b_8633232e18a44c688e262a39dceee71f","parameters":[{"name":"jsessionid","value":"F49D6BA232489FC1DBB8A3F1FE1A2BF1.uat01-vm-con03"}]}
     * risk : {"score":"100"}
     * buildNumber : e44aa8a3193e15aad2dd97f8dd7e3337d69d4df6@2019-11-05 16:14:43 +0000
     * timestamp : 2019-11-07 08:35:16+0000
     * ndc : 8ac7a4c76d067d4f016d14f7c51b077b_8633232e18a44c688e262a39dceee71f
     */

    private String id;
    private String paymentType;
    private String paymentBrand;
    private String amount;
    private String currency;
    private String descriptor;
    private String merchantTransactionId;
    private ResultBean result;
    private ResultDetailsBean resultDetails;
    private CardBean card;
    private CustomerBean customer;
    private RedirectBean redirect;
    private RiskBean risk;
    private String buildNumber;
    private String timestamp;
    private String ndc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentBrand() {
        return paymentBrand;
    }

    public void setPaymentBrand(String paymentBrand) {
        this.paymentBrand = paymentBrand;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public String getMerchantTransactionId() {
        return merchantTransactionId;
    }

    public void setMerchantTransactionId(String merchantTransactionId) {
        this.merchantTransactionId = merchantTransactionId;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public ResultDetailsBean getResultDetails() {
        return resultDetails;
    }

    public void setResultDetails(ResultDetailsBean resultDetails) {
        this.resultDetails = resultDetails;
    }

    public CardBean getCard() {
        return card;
    }

    public void setCard(CardBean card) {
        this.card = card;
    }

    public CustomerBean getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerBean customer) {
        this.customer = customer;
    }

    public RedirectBean getRedirect() {
        return redirect;
    }

    public void setRedirect(RedirectBean redirect) {
        this.redirect = redirect;
    }

    public RiskBean getRisk() {
        return risk;
    }

    public void setRisk(RiskBean risk) {
        this.risk = risk;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNdc() {
        return ndc;
    }

    public void setNdc(String ndc) {
        this.ndc = ndc;
    }

    public static class ResultBean {
        /**
         * code : 000.200.000
         * description : transaction pending
         */

        private String code;
        private String description;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static class ResultDetailsBean {
        /**
         * ExtendedDescription : Transaction pending
         * AcquirerResponse : PENDING
         */

        private String ExtendedDescription;
        private String AcquirerResponse;

        public String getExtendedDescription() {
            return ExtendedDescription;
        }

        public void setExtendedDescription(String ExtendedDescription) {
            this.ExtendedDescription = ExtendedDescription;
        }

        public String getAcquirerResponse() {
            return AcquirerResponse;
        }

        public void setAcquirerResponse(String AcquirerResponse) {
            this.AcquirerResponse = AcquirerResponse;
        }
    }

    public static class CardBean {
        /**
         * bin : 420000
         * last4Digits : 0000
         * holder :  Abdullah
         * expiryMonth : 10
         * expiryYear : 2020
         */

        private String bin;
        private String last4Digits;
        private String holder;
        private String expiryMonth;
        private String expiryYear;

        public String getBin() {
            return bin;
        }

        public void setBin(String bin) {
            this.bin = bin;
        }

        public String getLast4Digits() {
            return last4Digits;
        }

        public void setLast4Digits(String last4Digits) {
            this.last4Digits = last4Digits;
        }

        public String getHolder() {
            return holder;
        }

        public void setHolder(String holder) {
            this.holder = holder;
        }

        public String getExpiryMonth() {
            return expiryMonth;
        }

        public void setExpiryMonth(String expiryMonth) {
            this.expiryMonth = expiryMonth;
        }

        public String getExpiryYear() {
            return expiryYear;
        }

        public void setExpiryYear(String expiryYear) {
            this.expiryYear = expiryYear;
        }
    }

    public static class CustomerBean {
        /**
         * givenName : Abdullah
         * surname : Abdullah
         * companyName : misdaqia
         * email : abdullah@gmail.com
         */

        private String givenName;
        private String surname;
        private String companyName;
        private String email;

        public String getGivenName() {
            return givenName;
        }

        public void setGivenName(String givenName) {
            this.givenName = givenName;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public static class RedirectBean {
        /**
         * url : https://test.ppipe.net/connectors/migs/redirect.link;jsessionid=F49D6BA232489FC1DBB8A3F1FE1A2BF1.uat01-vm-con03?ndcid=8ac7a4c76d067d4f016d14f7c51b077b_8633232e18a44c688e262a39dceee71f
         * parameters : [{"name":"jsessionid","value":"F49D6BA232489FC1DBB8A3F1FE1A2BF1.uat01-vm-con03"}]
         */

        private String url;
        private List<ParametersBean> parameters;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<ParametersBean> getParameters() {
            return parameters;
        }

        public void setParameters(List<ParametersBean> parameters) {
            this.parameters = parameters;
        }

        public static class ParametersBean {
            /**
             * name : jsessionid
             * value : F49D6BA232489FC1DBB8A3F1FE1A2BF1.uat01-vm-con03
             */

            private String name;
            private String value;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }

    public static class RiskBean {
        /**
         * score : 100
         */

        private String score;

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }
    }
}
