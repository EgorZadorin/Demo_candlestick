package org.candles;

import joinery.DataFrame;
import utils.TimeUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CandlestickChartDemo {
    public static void main(String[] args) throws IOException {

        // Reading dataframe from csv

        DataFrame df_csv = DataFrame.readCsv("src/main/resources/instrument1.csv", ",");

        List<String> rows = Arrays.asList("candle1", "candle2");
        List<String> columns = Arrays.asList("open_ts", "open", "high", "low", "close", "close_ts");

        // Creating empty demo dataframe for OHCL-oriented candlestickchart

        DataFrame<Object> df_ohlc = new DataFrame<> (rows, columns);

        int rowStart = 0;
        int rowEnd;

        for (int i = 0; i < rows.size(); i++ ) {
            rowEnd = rowStart;

            // Parsing dataset in one-minute candles, better realization needed (13:00:00, 13:00:30, 13:01:31, 13:01:33)

            while(rowEnd < df_csv.length() && (TimeUtils.convertToSecondstime("" + df_csv.get(rowEnd, "Time")) -
                    TimeUtils.convertToSecondstime("" + df_csv.get(rowStart, "Time"))) < 60) { ++rowEnd; }

            DataFrame df_tmp = df_csv.slice(rowStart, rowEnd);

            // OHCL-logic

            df_ohlc.set(i, 0, df_csv.get(rowStart, "Time"));
            df_ohlc.set(i, 1, df_csv.get(rowStart, "Price"));
            df_ohlc.set(i, 2, Collections.max(df_tmp.col("Price")));
            df_ohlc.set(i, 3, Collections.min(df_tmp.col("Price")));
            df_ohlc.set(i, 4, df_csv.get(rowEnd -1, "Price"));
            df_ohlc.set(i, 5, df_csv.get(rowEnd - 1, "Time"));

            rowStart = rowEnd;
        }

        System.out.println(df_ohlc);
    }
}
