import collection.mutable.ArrayBuffer

/**
1.get a array of n, element random [0,n)
*/
def n_rand(n: Int) = {
	var r = scala.util.Random
	var ret = new Array[Int](n)
	for(i <- 0 until n) ret(i) = r.nextInt(n)
	ret
}
println(n_rand(12).mkString(", "))

/**
2.swap adjacent element,(1,2,3,4,5)->(2,1,4,3,5)
*/
def x_swap(a:Array[Int]){
	for(i <- 0 until (a.length, 2) if i+1 < a.length){
		var tmp = a(i)
		a(i) = a(i+1)
		a(i+1) = tmp
	}
	println(a.mkString(","))
}
x_swap(Array(1,2,3,4,5))
x_swap(Array(1,2,3,4,5,6))

/**
3.repeat ex-2 by for/yield, produce new array 
*/
def x_swap_2(a:Array[Int]){
	var ret = for(elem <- a) yield(elem)
	x_swap(ret)
}
x_swap_2(Array(1,2,3,4,5))

/**
4.get a new array, positive number front, non-positive number behind, (1,-1,3,-5,0,7)->(1,3,7,-1,-5,0) 
*/
def x_change(a:Array[Int]):Array[Int]={
	var pos = for(e <- a if e > 0) yield e
	var non_pos = for(e <- a if e <= 0) yield e
	pos++non_pos
}
println(x_change(Array(1,-1,3,-5,0,7)).mkString(","))

/**
5.how to calculate mean of Array[Double] 
*/
def x_mean(a:Array[Double]):Double={a.sum/a.length}
println(x_mean(Array(1.0,2.0,3.0,4.0)))

/**
6.reverse Array or ArrayBuffer
*/
def x_reverse(a:Array[Int]){
	var l = 0
	var r = a.length-1
	while(l < r){
		var tmp = a(r)
		a(r) = a(l)
		a(l) = tmp
		l += 1
		r -= 1
	}
	println(a.mkString(","))
}
x_reverse(Array(1,2,3,4,5))

def x_reverse_1(a:ArrayBuffer[Int]):ArrayBuffer[Int]={
	a.reverse
}
val a = x_reverse_1(ArrayBuffer(1,2,3,4,5))
println(a.mkString(","))

/**
7.remove duplicate number 
*/
Array(1,2,1,3,4,2,5,8,1,3,7).distinct foreach println _

/**
8.compare 3.4 two solution
*/
def time[R](block: => R):R = {
	var t0 = System.nanoTime()
	var result = block
	var t1 = System.nanoTime()
	println("execute time: " + (t1 - t0) + "ns")
	result
}
// produce numbers
var test_nums_1 = ArrayBuffer((for(i <- 0 until 100000) yield util.Random.nextInt(100)).toBuffer: _*)
var test_nums_2 = for(x<-test_nums_1) yield x
var test_nums_3 = for(x<-test_nums_1) yield x

// solution 1
def solve_1(a:ArrayBuffer[Int]){
	var first = true
	var n = a.length
	var i = 0
	while(i < n){
		if(a(i) >= 0) i += 1
		else if(first){ first = false; i += 1 }
		else { a.remove(i); n -= 1 }
	}
}

// solution 2
def solve_2(a:ArrayBuffer[Int]){
	var first = true
	var idxs = for(i <- 0 until a.length if first || a(i) > 0) yield {
		if(a(i) < 0) first = false
		i
	}
	for(j <- 0 until idxs.length) a(j) = a(idxs(j))
	a.trimEnd(a.length-idxs.length)
}
// solution 3
def solve_3(a:ArrayBuffer[Int]){
	var idxs = (for(i <- 0 until a.length if a(i) < 0) yield i)
	for(j <- 1 until idxs.length) a.remove(idxs(j))
}

time{solve_1(test_nums_1)} //best speed
time{solve_2(test_nums_2)} //third speed
time{solve_3(test_nums_3)} //second speed
println(test_nums_1 == test_nums_2)
println(test_nums_1 == test_nums_3)

/**
9.using java library related 
*/
var time_zone = java.util.TimeZone.getAvailableIDs.filter(_.startsWith("America")).map(_.replaceFirst("America/", "")).sorted
time_zone.foreach(println _)

/**
10.using java library related 
*/
import java.awt.datatransfer._
import collection.mutable.Buffer
import collection.JavaConversions._
val flavors = SystemFlavorMap.getDefaultFlavorMap.asInstanceOf[SystemFlavorMap]

val res: Buffer[String] = flavors.getNativesForFlavor(DataFlavor.imageFlavor)
println(res)
