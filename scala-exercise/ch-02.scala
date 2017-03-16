def my_pow(x:Double, n:Int):Double={
	if(n == 0) 1
	else if(n < 0) 1.0/my_pow(x, -n)
	else if(n % 2 == 0) my_pow(x, n/2)*my_pow(x, n/2)
	else x*my_pow(x, n-1)
}
println(my_pow(2,2))
println(my_pow(2,-2))
