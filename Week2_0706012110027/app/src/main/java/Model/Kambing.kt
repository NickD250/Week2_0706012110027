package Model

class Kambing (name: String, age: Int, imageUri: String) : Animal(name, age, imageUri) {
    override fun interaction(): String {
        super.interaction()
        var binatang = "Embhecks Embhecks~"

        return binatang;
    }

    override fun feed(): String {
        super.feed()
        var binatang = "Kamu memberi makan hewanmu a juicy goat satay"

        return binatang;
    }
}