package com.test.model;

import java.util.HashSet;
import java.util.Set;

public class PlaceGraph {

	private Set<PlaceNode> nodes = new HashSet<>();

	public void addNode(PlaceNode node) {
		nodes.add(node);
	}

	public Set<PlaceNode> getNodes() {
		return nodes;
	}

	public void setNodes(Set<PlaceNode> nodes) {
		this.nodes = nodes;
	}
}
