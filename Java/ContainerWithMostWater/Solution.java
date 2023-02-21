class Solution {
    public int maxArea(int[] height) {
        int first = 0;
        int last = height.length-1;
        int ans = 0;
        while(last != first)
        {
            ans = Math.max((last-first)*Math.min(height[first], height[last]), ans);
            if(height[first] < height[last])
            {
                ++first;
            } else {
                --last;
            }
        }
        return ans;
    }

    public static void main(String[] args)
    {
        Solution test = new Solution();
        int[] input = {1,8,6,2,5,4,8,3,7};
        System.out.println(test.maxArea(input));
    }
}