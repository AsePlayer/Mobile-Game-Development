// Code by Ryan Scott
class MusicPlayer {
    private var songs: Array<String> = arrayOf()
    //your code goes here
    // add: add the given argument track to the tracks array.
    fun add(song:String) {
        songs += song
    }
    // show: output all track names in the player on separate lines.
    fun show() {
        for (song in songs) {
            println(song)
        }
    }
    // play: start playing the first track by outputting "Playing name" where name is the first track name.
    fun play() {
        println("Playing " + songs[0])
    }
}
fun main(args: Array<String>) {
    val m = MusicPlayer()
    
    while(true) {
        var input = readLine()!!
        if(input == "stop") {
            break
        }
        m.add(input)
    }
    m.show()
    m.play()
}