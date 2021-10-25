package com.project.got.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class BattleResponse<T>
{
    private String errormessage;
    private HttpStatus httpStatus;
    private LocalDateTime datetime;
    private  T response;


    public BattleResponse(String errormessage, HttpStatus httpStatus, LocalDateTime datetime, T response)
    {
        this.errormessage = errormessage;
        this.httpStatus = httpStatus;
        this.response = response;
        this.datetime = datetime;
    }

    public String getErrormessage()
    {
        return errormessage;
    }

    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }

    public HttpStatus getHttpStatus()
    {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus)
    {
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getDatetime()
    {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime)
    {
        this.datetime = datetime;
    }

    public T getResponse()
    {
        return response;
    }

    public void setResponse(T response)
    {
        this.response = response;
    }
}
