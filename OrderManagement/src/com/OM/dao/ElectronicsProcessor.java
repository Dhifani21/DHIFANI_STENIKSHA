package com.OM.dao;

import com.OM.entity.Electronics;
import java.util.ArrayList;
import java.util.List;

public class ElectronicsProcessor extends ProductProcessor {
    private List<Electronics> electronics = new ArrayList<>();

    public void createElectronics(Electronics electronic) {
        createProduct(electronic);
        electronics.add(electronic);
    }

    public List<Electronics> getAllElectronics() {
        return electronics;
    }
}