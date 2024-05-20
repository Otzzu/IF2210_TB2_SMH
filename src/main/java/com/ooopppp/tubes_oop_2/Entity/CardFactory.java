package com.ooopppp.tubes_oop_2.Entity;

public class CardFactory {

    public Card createCard(String type) {
        return switch (type) {
            case "HIU_DARAT" -> new Carnivore("Hiu Darat", new Product("Sirip Hiu", 500, 12, "/shark-fin.png"), 20, "/hiu_darat.png");
            case "SAPI" -> new Herbivore("Sapi", new Product("Susu", 100, 4, "/susu.png"), 10, "/cow.png");
            case "DOMBA" -> new Herbivore("Domba", new Product("Daging Domba", 120, 6, "/Daging_Domba.png"), 12, "/sheep.png");
            case "KUDA" -> new Herbivore("Kuda", new Product("Daging Kuda", 150, 8, "/Daging_Kuda.png"), 14, "/horse.png");
            case "AYAM" -> new Omnivore("Ayam", new Product("Telur", 50, 2, "/telur.png"), 5, "/chicken.png");
            case "BERUANG" -> new Omnivore("Beruang", new Product("Daging Beruang", 500, 12, "/Daging_Beruang.png"), 25, "/bear.png");
            case "BIJI_JAGUNG" -> new Plant("Biji Jagung", new Product("Jagung", 150, 3, "/corn.png"), 3, "/corn_seeds.png");
            case "BIJI_LABU" -> new Plant("Biji Labu", new Product("Labu", 500, 5, "/pumpkin.png"), 5, "/pumpkin_seeds.png");
            case "BIJI_STROBERI" -> new Plant("Biji Stroberi", new Product("Stroberi", 350, 5, "/strawberry.png"), 4, "/strawberry_seeds.png");
            case "ACCELERATE" -> new ItemAdded("Accelerate", 8, 2, "/Accelerate.png");
            case "DELAY" -> new ItemAdded("Delay", -5, -2, "/Delay.png");
            case "INSTANT_HARVEST" -> new Item("Instant Harvest", "/Instant_Harvest.png");
            case "DESTROY" -> new Item("Destroy", "/Destroy.png");
            case "PROTECT" -> new Item("Protect", "/Protect.png");
            case "TRAP" -> new Item("Trap", "/bear_trap");
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
