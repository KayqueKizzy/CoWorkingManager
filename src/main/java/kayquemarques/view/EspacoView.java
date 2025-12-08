package kayquemarques.view;

import kayquemarques.dao.EspacoDTO;

import java.util.Scanner;

import static java.lang.Double.parseDouble;

public class EspacoView {
    Scanner sc;
    EspacoDTO dto;
    public EspacoView() {}
    public EspacoDTO criarEspaco(String espacoTipo) {
        sc = new Scanner(System.in);
        dto = new EspacoDTO();
        System.out.println("Digite o nome do espaço:");
        dto.setNome(sc.nextLine());
        System.out.println("Digite a capacidade do espaço:");
        dto.setCapacidade(sc.nextInt());
        sc.nextLine();
        System.out.println("Digite o preço por hora do espaço:");
        dto.setPrecoPorHora(parseDouble(sc.next()));
        sc.nextLine();
        System.out.println("O espaço está disponível? (true/false):");
        dto.setDisponivel(true);

        switch (espacoTipo) {
            case "1": {
                dto.setTIPO("Cabine Individual");
                break;
            }
            case "2": {
                dto.setTIPO("Auditório");
                System.out.println("O auditório tem palco? (true/false):");
                dto.setTemPalco(sc.nextBoolean());
                System.out.println("Digite a capacidade extra do auditório:");
                dto.setCapacidadeExtra(sc.nextInt());
                break;
            }
            case "3": {
                dto.setTIPO("Sala de Reuniao");
                dto.setUsaProjetor(sc.nextBoolean());
                break;
            }
            default:
                throw new IllegalArgumentException("Tipo de espaço desconhecido: " + espacoTipo);
        }
        return dto;
    }
    public int solicitarIdEspaco(){
        sc = new Scanner(System.in);
        System.out.println("Digite o ID do espaço:");
        return sc.nextInt();
    }

}
