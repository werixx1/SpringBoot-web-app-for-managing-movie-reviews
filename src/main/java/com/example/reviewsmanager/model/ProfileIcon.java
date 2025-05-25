package com.example.reviewsmanager.model;
import lombok.Getter;

@Getter
public enum ProfileIcon
{
    SMILE(":)"),
    FROWN(":("),
    WINK(";)"),
    SILLY(":P"),
    SURPRISE(":0"),
    CHILL("B)");

    private final String symbol;
    ProfileIcon(String symbol)
    {
        this.symbol = symbol;
    }
}


