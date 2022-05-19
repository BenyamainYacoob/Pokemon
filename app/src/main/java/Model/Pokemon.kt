package Model

class Pokemon {
    var id = 0
    var num: String? = null
    var name: String? = null
    var img: String? = null
    var type: List<String>? = null
    var height: String? = null
    var weight: String? = null
    var candy: String? = null
    var candy_count = 0
    var egg: String? = null
    var spawn_chance = 0.0
    var avg_spawns = 0.0
    var spawn_time: String? = null
    var multipliers: List<Double>? = null
    var weaknesses: List<String>? = null

    constructor() {}
    constructor(
        id: Int,
        num: String?,
        name: String?,
        img: String?,
        type: List<String>?,
        height: String?,
        weight: String?,
        candy: String?,
        candy_count: Int,
        egg: String?,
        spawn_chance: Double,
        avg_spawns: Double,
        spawn_time: String?,
        multipliers: List<Double>?,
        weaknesses: List<String>?
    ) {
        this.id = id
        this.num = num
        this.name = name
        this.img = img
        this.type = type
        this.height = height
        this.weight = weight
        this.candy = candy
        this.candy_count = candy_count
        this.egg = egg
        this.spawn_chance = spawn_chance
        this.avg_spawns = avg_spawns
        this.spawn_time = spawn_time
        this.multipliers = multipliers
        this.weaknesses = weaknesses
    }
}