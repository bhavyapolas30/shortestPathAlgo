package com.text.utils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

import com.test.model.PlaceGraph;
import com.test.model.PlaceNode;

public class ShortestPathUtil
{
	public static PlaceGraph calculatePathFromSource(PlaceGraph sampleGraph, PlaceNode sourceNode) 
	{
		sourceNode.setDistance(0);

		Set<PlaceNode> settledNodes = new HashSet<>();
		Set<PlaceNode> unsettledNodes = new HashSet<>();

		unsettledNodes.add(sourceNode);

		while (unsettledNodes.size() != 0) 
		{
			PlaceNode currentNode = getLowestDistanceNode(unsettledNodes);
			unsettledNodes.remove(currentNode);
			
			for (Entry < PlaceNode, Integer> adjacencyPair: currentNode.getAdjacentNodes().entrySet())
			{
				PlaceNode adjacentNode = adjacencyPair.getKey();
				Integer edgeWeight = adjacencyPair.getValue();
				
				if (!settledNodes.contains(adjacentNode)) 
				{
					calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
					unsettledNodes.add(adjacentNode);
				}
			}
			
			settledNodes.add(currentNode);
		}
		
		return sampleGraph;
	}

	private static PlaceNode getLowestDistanceNode(Set < PlaceNode > unsettledNodes) 
	{
		PlaceNode lowestDistanceNode = null;
		int lowestDistance = Integer.MAX_VALUE;
		
		for (PlaceNode node: unsettledNodes) 
		{
			int nodeDistance = node.getDistance();
			
			if (nodeDistance < lowestDistance) 
			{
				lowestDistance = nodeDistance;
				lowestDistanceNode = node;
			}
		}
		
		return lowestDistanceNode;
	}

	private static void calculateMinimumDistance(PlaceNode evaluationNode, Integer edgeWeigh, PlaceNode sourceNode)
	{
		Integer sourceDistance = sourceNode.getDistance();
		
		if (sourceDistance + edgeWeigh < evaluationNode.getDistance())
		{
			evaluationNode.setDistance(sourceDistance + edgeWeigh);
			LinkedList<PlaceNode> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
			shortestPath.add(sourceNode);
			evaluationNode.setShortestPath(shortestPath);
		}
	}

}
