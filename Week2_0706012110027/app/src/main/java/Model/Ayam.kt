package Model

import Database.Array

class Ayam (name: String, age: Int, imageUri: String) : Animal(name, age, imageUri) {
    override fun interaction(): String {
        super.interaction()
        var binatang = "PetokPetokPetok"

        return binatang;
    }

    override fun feed(): String {
        super.feed()
        var binatang = "Kamu memberi makan hewanmu a crispy Chicken Leg"

        return binatang;
    }
}