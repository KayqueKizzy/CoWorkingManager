package kayquemarques.view;

import kayquemarques.dao.ReservaDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ReservaView {
    Scanner sc;
    ReservaDTO dto;
    ReservaDTO reserva;

    public ReservaView() {
    }

    public ReservaDTO criarReserva() {
        sc = new Scanner(System.in);
        dto = new ReservaDTO();
        reserva = new ReservaDTO();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        System.out.println("Digite o ID da Reserva a ser reservado:");
        reserva.setId(Integer.parseInt(sc.nextLine()));

        try{
            System.out.println("Digite o Inicio da Reserva a ser reservado (formato: yyyy-MM-ddTHH:mm):");
            String inicio = sc.nextLine();;
            LocalDateTime inicioDate = LocalDateTime.parse(inicio, dtf);
            reserva.setInicio(inicioDate);

            System.out.println("Digite o Fim da Reserva a ser reservado:");
            String fim = sc.nextLine();
            LocalDateTime fimDate = LocalDateTime.parse(fim, dtf);
            reserva.setFim(fimDate);
        } catch (Exception e){
            System.out.println("Formato de data inválido.");
        }
        return dto;
    }
}