package Model


class Sapi(name: String, age: Int, imageUri: String) : Animal(name, age, imageUri) {
    override fun interaction(): String {
        super.interaction()
        var binatang = "muwwwwwwwww"

        return binatang;
    }

    override fun feed(): String {
        super.feed()
        var binatang = "Kamu memberi makan hewanmu a juciy steak"

        return binatang;
    }
}