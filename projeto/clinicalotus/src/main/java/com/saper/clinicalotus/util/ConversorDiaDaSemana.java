package com.saper.clinicalotus.util;
import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

public class ConversorDiaDaSemana {
    private static final Map<String, String> mapaPortuguesParaIngles;
    private static final Map<String, String> mapaInglesParaPortugues;

    static {
        mapaPortuguesParaIngles = new HashMap<>();
        mapaPortuguesParaIngles.put("segunda-feira", "MONDAY");
        mapaPortuguesParaIngles.put("terça-feira", "TUESDAY");
        mapaPortuguesParaIngles.put("quarta-feira", "WEDNESDAY");
        mapaPortuguesParaIngles.put("quinta-feira", "THURSDAY");
        mapaPortuguesParaIngles.put("sexta-feira", "FRIDAY");
        mapaPortuguesParaIngles.put("sábado", "SATURDAY");
        mapaPortuguesParaIngles.put("domingo", "SUNDAY");

        mapaInglesParaPortugues = new HashMap<>();
        for (Map.Entry<String, String> entry : mapaPortuguesParaIngles.entrySet()) {
            mapaInglesParaPortugues.put(entry.getValue(), entry.getKey());
        }
    }

    public static DayOfWeek converterParaIngles(String diaSemana) {
        return DayOfWeek.valueOf(mapaPortuguesParaIngles.get(diaSemana));
    }

    public static String converterParaPortugues(DayOfWeek diaDaSemana) {
        return mapaInglesParaPortugues.get(diaDaSemana.name());
    }

}
