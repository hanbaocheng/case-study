package com.medion.trakttv.utils;

import java.lang.reflect.Method;

/**
 * Created by bhan on 7/13/16.
 */
public enum HttpStatus {
    UNKNOWN(0),
    STATUS_200(200),
    STATUS_201(201),
    STATUS_204(204),
    STATUS_400(400),
    STATUS_401(401),
    STATUS_403(403),
    STATUS_404(404),
    STATUS_405(405),
    STATUS_409(409),
    STATUS_412(412),
    STATUS_422(422),
    STATUS_429(429),
    STATUS_500(500),
    STATUS_503(503),
    STATUS_504(504),
    STATUS_520(520),
    STATUS_521(521),
    STATUS_522(522);

    private int value;

    HttpStatus(int statusCode) {
        this.value = statusCode;
    }


    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        switch (value) {
            case 200:
                return "Success";
            case 201:
                return "	Success - new resource created (POST)";
            case 204:
                return "	Success - no content to return (DELETE)";
            case 400:
                return "	Bad Request - request couldn't be parsed";
            case 401:
                return "	Unauthorized - OAuth must be provided";
            case 403:
                return "	Forbidden - invalid API key or unapproved app";
            case 404:
                return "	Not Found - method exists, but no record found";
            case 405:
                return "	Method Not Found - method doesn't exist";
            case 409:
                return "	Conflict - resource already created";
            case 412:
                return "	Precondition Failed - use application/json content type";
            case 422:
                return "	Unprocessible Entity - validation errors";
            case 429:
                return "	Rate Limit Exceeded";
            case 500:
                return "	Server Error";
            case 503:
                return "	Service Unavailable - server overloaded (try again in 30s)";
            case 504:
                return "	Service Unavailable - server overloaded (try again in 30s)";
            case 520:
                return "	Service Unavailable - Cloudflare error";
            case 521:
                return "	Service Unavailable - Cloudflare error";
            case 522:
                return "	Service Unavailable - Cloudflare error";
        }
        return "Network not connected.";
    }

    public static HttpStatus getEnum(int statuCode) {
        for (HttpStatus v : values())
            if (v.getValue() == statuCode) return v;
        throw new IllegalArgumentException();
    }
}
