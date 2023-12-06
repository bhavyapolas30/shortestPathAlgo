package com.test.controller;

import java.util.function.Consumer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.PlaceGraph;
import com.test.model.PlaceNode;
import com.text.utils.ShortestPathUtil;

@RestController
public class WebController {

	@GetMapping("/findShortestPath/{destPath}")
	public String findShortestPath(@PathVariable("destPath") String destPath)
	{
		StringBuilder sb = new StringBuilder();
		PlaceGraph updatedGraph =  getShortestPath();

		PlaceNode destinationNode = updatedGraph.getNodes().stream()
				.filter(node -> node.getName().equals(destPath))
				.findFirst()
				.get();

		Consumer<PlaceNode> printShortestPath = (PlaceNode x) -> {
			sb.append(x.getName().toString() +"--->");
		};

		destinationNode.getShortestPath().stream().forEach(printShortestPath);
		sb.append(destPath);

		return "The shortest path between source and destination is " +sb.toString() + " and total distance is " +destinationNode.getDistance().toString();
	}

	private PlaceGraph getShortestPath()
	{
		PlaceNode nodeA = new PlaceNode("A");
		PlaceNode nodeB = new PlaceNode("B");
		PlaceNode nodeC = new PlaceNode("C");
		PlaceNode nodeD = new PlaceNode("D"); 
		PlaceNode nodeE = new PlaceNode("E");
		PlaceNode nodeF = new PlaceNode("F");

		nodeA.addDestination(nodeB, 10);
		nodeA.addDestination(nodeC, 15);

		nodeB.addDestination(nodeD, 12);
		nodeB.addDestination(nodeF, 15);

		nodeC.addDestination(nodeE, 10);

		nodeD.addDestination(nodeE, 2);
		nodeD.addDestination(nodeF, 1);

		nodeF.addDestination(nodeE, 5);

		PlaceGraph graph = new PlaceGraph();

		graph.addNode(nodeA);
		graph.addNode(nodeB);
		graph.addNode(nodeC);
		graph.addNode(nodeD);
		graph.addNode(nodeE);
		graph.addNode(nodeF);

		return ShortestPathUtil.calculatePathFromSource(graph, nodeA);
	}
}
