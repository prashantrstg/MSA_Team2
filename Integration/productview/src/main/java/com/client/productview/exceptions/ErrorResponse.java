package com.client.productview.exceptions;

public class ErrorResponse {

    

    private String errorMessage;

   

    private int statusCode;

   

    public ErrorResponse(String errorMessage) {

           this.errorMessage = errorMessage;

    }



    /**

    * @return the errorMessage

    */

    public String getErrorMessage() {

           return errorMessage;

    }



    /**

    * @param errorMessage the errorMessage to set

    */

    public void setErrorMessage(String errorMessage) {

           this.errorMessage = errorMessage;

    }



    /**

    * @return the statusCode

    */

    public int getStatusCode() {

           return statusCode;

    }



    /**

    * @param statusCode the statusCode to set

    */

    public void setStatusCode(int statusCode) {

           this.statusCode = statusCode;

    }

   



}
