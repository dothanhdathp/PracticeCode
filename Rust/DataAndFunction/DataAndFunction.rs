fn main()
{
    // This value can not be changed
    let constant_value = 0;
    println!("Constant value go with \"let\" {constant_value}");
    // This value can be changed
    let mut unconstant_value = 10;
    println!("Non-constant value go with \"mut\" {unconstant_value}");
    unconstant_value = 20;
    println!("Non-constant value after changed \"mut\" {unconstant_value}");
    // Rust get value follow the name of value
    println!("Let try with another data type!");
}