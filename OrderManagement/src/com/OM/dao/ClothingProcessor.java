package com.OM.dao;

import com.OM.entity.Clothing;
import java.util.ArrayList;
import java.util.List;

public class ClothingProcessor extends ProductProcessor {
    private List<Clothing> clothes = new ArrayList<>();

    public void createClothing(Clothing clothing) {
        createProduct(clothing);
        clothes.add(clothing);
    }

    public List<Clothing> getAllClothes() {
        return clothes;
    }
}