//1.create a map, change it
val m_map = Map("mac"->1500, "iphone"->800, "ipad"->600)
val m_map_2 = for((k,v) <- m_map) yield (k, v*0.9)
for((k,v) <- m_map_2) println(k,v)
//2.read a file, get word and count
import io.Source
def count_word(){
	val source = Source.fromFile("../README.md")
	val word_count = collection.mutable.Map[String, Int]().withDefault(_ => 0)
	val content = source.mkString.split("\\s+")
	for (word <- content) word_count(word) += 1
	println(word_count)
}
count_word()

//3.repeat exercise 2 with a immutable map
def countWordByImmutableMap(){
	val source = Source.fromFile("../README.md")
	var word_count = Map[String, Int]()
	val content = source.mkString.split("\\s+")
	for (word <- content) word_count += (word -> (word_count.getOrElse(word, 0) + 1))
	println(word_count)
}
countWordByImmutableMap()

//4.repeat exercise 2 with a sorted map
def countWordBySortedMap(){
	val source = Source.fromFile("../README.md")
	var word_count = collection.immutable.SortedMap[String, Int]()
	val content = source.mkString.split("\\s+")
	for (word <- content) word_count += (word -> (word_count.getOrElse(word, 0) + 1))
	println(word_count)
}
countWordBySortedMap()

//5.repeat exercise 2 with a java tree map
def countWordByTreeMap(){
	import scala.collection.JavaConversions.mapAsScalaMap
	val source = Source.fromFile("../README.md")
	val word_count:collection.mutable.Map[String, Int] = new java.util.TreeMap[String, Int]
	val content = source.mkString.split("\\s+")
	for (word <- content) if(word_count.contains(word)) word_count(word) += 1 else word_count(word) = 1
	println(word_count)
}
countWordByTreeMap()

//6.link map study
def usingLinkedMap(){
	import java.util.Calendar._
	val m_map = collection.mutable.LinkedHashMap(
		"Monday" -> MONDAY,
		"Tuesday" -> TUESDAY,
		"Wednesday" -> WEDNESDAY,
		"Thursday" -> THURSDAY,
		"Friday" -> FRIDAY,
		"Saturday" -> SATURDAY,
		"Sunday" -> SUNDAY
	)
	println(m_map)
}
usingLinkedMap()

//7.print java property table
import collection.JavaConversions.propertiesAsScalaMap
import collection.JavaConversions.mapAsScalaMap
val props = propertiesAsScalaMap(System.getProperties())
val max_len = props.keySet.maxBy(_.length).length
for((k,v) <- props) printf("%-"+max_len+"s | %s\n", k,v) // %-12s print string of length 12, left aligned 

//8. return min,max 
def minmax(x:Array[Int])={
	(x.min,x.max)
}
println(minmax(Array(1,2,3,4,5)))

//9. return <v,=v,>v count 
def lteqgt(x:Array[Int], v:Int)={
	(x.count(_ < v), x.count(_ == v), x.count(_ > v))
}
println(lteqgt(Array(1,2,3,4,5),3))

//10.zip
println("Hello".zip("World"))

