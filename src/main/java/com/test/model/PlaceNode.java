package com.test.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PlaceNode {

	private String name;

	private List<PlaceNode> shortestPath = new LinkedList<>();

	private Integer distance = Integer.MAX_VALUE;

	Map<PlaceNode, Integer> adjacentNodes = new HashMap<>();

	public PlaceNode(String name) {
		this.name = name;
	}

	public void addDestination(PlaceNode destination, int distance) {
		adjacentNodes.put(destination, distance);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PlaceNode> getShortestPath() {
		return shortestPath;
	}

	public void setShortestPath(List<PlaceNode> shortestPath) {
		this.shortestPath = shortestPath;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Map<PlaceNode, Integer> getAdjacentNodes() {
		return adjacentNodes;
	}

	public void setAdjacentNodes(Map<PlaceNode, Integer> adjacentNodes) {
		this.adjacentNodes = adjacentNodes;
	}
}
