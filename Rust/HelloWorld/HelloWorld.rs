fn update(x:&mut i32, y:&mut i32) {
    *x = 20;
    *y = 20;
    println!("update: x = {}; y = {}", *x, *y);
}

fn main()
{
    let mut x:i32 = 10;
    let mut y:i32 = 10;
    println!("main: x = {x}; y = {y}");
    update(&mut x, &mut y);
    println!("main: x = {x}; y = {y}");
}