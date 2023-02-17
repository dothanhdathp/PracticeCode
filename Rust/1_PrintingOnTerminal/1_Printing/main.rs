fn main() {
	let constant_value = 0;
	// 'let' creat constant value, that mean the value can not be change
	println!("Let start the constant_value = {}", constant_value);
	// if need value could be change, go with 'let mut' (mutable)
	let mut non_constant_value = 10;
	println!("Init non_constant_value = {}", non_constant_value);
	println!("Then change non_constant_value value to 20");
	non_constant_value += 20;
	println!("New non_constant_value = {}", non_constant_value);
	// Also, we can call directly like the name the follow
	println!("Constant_value = {constant_value}, non_constant_value = {non_constant_value}");
}