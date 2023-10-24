class MyClass
{
	public void MyClass() {};

	public String TAG = "MyClass";
	public String[] list = new String[] {};

	public String[] getList() {
		return list;
	}

	public void setList(int id) {
        if(id == 1) {
        	list = new String[] {"Tacker", "Scratch", "Poud", "Heavy Slam"};
        }
        if(id == 2) {
        	list = new String[] {"Tacker", "Body Slam", "Confusion", "Heavy Slam"};
        }
        if(id == 3) {
        	list = new String[] {"Ember", "Sonic boom", "Earthquake", "Bellydump"};
        }
    }
}