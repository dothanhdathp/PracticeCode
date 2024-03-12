#!/usr/bin/env python3
import rclpy
from rclpy.node import Node

class NodeInfo(Node):
    def __init__(self):
        super().__init__("Hello_Node")
        self.get_logger().info("Info: Hello World Node")

# args equal none due to unused
def main(args=None):
    rclpy.init(args=args)
    node = NodeInfo()
    # rclpy.spin(node)
    rclpy.shutdown()

if __name__ == '__main__':
    main()