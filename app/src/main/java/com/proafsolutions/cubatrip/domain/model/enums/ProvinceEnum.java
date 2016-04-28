package com.proafsolutions.cubatrip.domain.model.enums;

/**
 * Created by alejandro.clavijo on 4/28/2016.
 */
public enum ProvinceEnum {

    PINAR(1,"Pinar del Rio"),
    ARTEMISA(2, "Artemisa"),
    LA_HABANA(3, "La Habana"),
    MAYABEQUE(4, "Mayabeque"),
    MATANZAS(5, "Matanzas"),
    CIENFUEGOS(6, "Cienfuegos"),
    VILLA_CLARA(7, "Villa Clara"),
    SANCTI_SPIRITUS(8, "Sancti Spiritus"),
    CIEGO(9, "Ciego"),
    CAMAGUEY(10, "Camaguey"),
    LAS_TUNAS(11, "Las Tunas"),
    GRANMA(12, "Granma"),
    HOLGUIN(13, "Holguin"),
    SANTIAGO(14, "Santiago de Cuba"),
    GUANTANAMO(15, "Guantanamo"),
    ISLA_DE_CUBA(16, "Isla de Cuba");

    private int code;
    private String descriptor;

    ProvinceEnum(int code, String descriptor){
        this.code = code;
        this.descriptor = descriptor;
    }

    public int getCode() {
        return code;
    }

    public String getDescriptor() {
        return descriptor;
    }
}
