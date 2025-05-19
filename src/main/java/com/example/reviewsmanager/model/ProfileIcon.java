package com.example.reviewsmanager.model;
import lombok.Getter;

@Getter
public enum ProfileIcon
{
    SMILE(":)"),
    FROWN(":("),
    WINK(";)"),
    SILLY(":P"),
    SURPRISE(":0");

    private final String symbol;
    ProfileIcon(String symbol)
    {
        this.symbol = symbol;
    }
}


