package kayquemarques.service.utils;

import kayquemarques.dao.EspacoDTO;
import kayquemarques.model.Auditorio;
import kayquemarques.model.CabineIndividual;
import kayquemarques.model.Espaco;
import kayquemarques.model.SalaDeReuniao;

import java.util.List;

public class ConversorDTO {

    public static Espaco conversorDTOToEspaco(EspacoDTO dto) {

        switch (dto.getTIPO()) {
            case "Cabine Individual": {
                return new CabineIndividual(dto);
            }
            case "Auditório": {
                return new Auditorio(dto);
            }
            case "Sala de Reuniao": {
                return new SalaDeReuniao(dto);
            }
            default:
                throw new IllegalArgumentException("Tipo de espaço desconhecido: " + dto.getTIPO());
        }



    }
    public static EspacoDTO conversorEspacoToDTO(Espaco espaco) {
        EspacoDTO dto = new EspacoDTO();

        dto.setId(espaco.getId());
        dto.setNome(espaco.getNome());
        dto.setCapacidade(espaco.getCapacidade());
        dto.setDisponivel(espaco.isDisponivel());
        dto.setPrecoPorHora(espaco.getPrecoPorHora());

        if (espaco instanceof CabineIndividual) {
            dto.setTIPO("Cabine Individual");
        } else if (espaco instanceof Auditorio) {
            dto.setTIPO("Auditório");
            Auditorio auditorio = (Auditorio) espaco;
            dto.setTemPalco(auditorio.isTemPalco());
            dto.setCapacidadeExtra(auditorio.getCapacidadeExtra());
        } else if (espaco instanceof SalaDeReuniao) {
            dto.setTIPO("Sala de Reuniao");
            SalaDeReuniao sala = (SalaDeReuniao) espaco;
            dto.setUsaProjetor(sala.isUsaProjetor());
        } else {
            throw new IllegalArgumentException("Tipo de espaço desconhecido: " + espaco.getClass().getSimpleName());
        }
        return dto;
    }
    public static List<Espaco> conversorListaDTOToListaEspaco(List<EspacoDTO> dtoList) {
        return dtoList.stream()
                .map(ConversorDTO::conversorDTOToEspaco)
                .toList();
    }
}