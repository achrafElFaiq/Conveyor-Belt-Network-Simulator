# Conveyor Belt Network Simulator

## Overview

The Conveyor Belt Network Simulator is a Java-based application designed to simulate and visualize conveyor belt networks. This simulator allows you to define networks consisting of belts, nodes (e.g., splitters, inputs, outputs), and their connections. The main goal of the simulator is to help in researching and optimizing conveyor belt networks.

## Features

- **Belt Network Representation**: Define networks consisting of belts connecting various nodes such as inputs, outputs, and splitters.
- **Graphical Visualization**: Visualize the network structure graphically, displaying the connections between nodes and the flow of items across the belts.
- **Customizable Belt Suppliers**: Easily generate networks with different configurations using predefined and custom belt suppliers.
- **Cycle Detection**: Identify and handle cycles within the network to ensure smooth operation.

## Project Structure

The project is organized into several packages, each serving a specific purpose:

- **`fr.univ.amu.conveyorbelts`**: Contains the core classes for the belt network, including `Belt`, `BeltList`, `BeltNetwork`, and utility classes like `CycleDetector`.
  
- **`fr.univ.amu.conveyorbelts.predefined`**: Includes predefined suppliers for generating belts and networks, such as `BeltSuppliers`.

- **`fr.univ.amu.conveyorbelts.encoders`**: Contains encoders like `GraphicalEncoder` for converting the network into different formats, including graphical representations.

- **`fr.univ.amu.conveyorbelts.nodes`**: Defines the nodes that make up the network, including input nodes, output nodes, and splitters.

- **`fr.univ.amu.conveyorbelts.tools`**: Utility classes that provide additional functionality for manipulating the network.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher.
- An Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse.

### Running the Project

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/conveyor-belt-network-simulator.git
   cd conveyor-belt-network-simulator
   ```

2. **Open the Project**:
   - Import the project into your preferred IDE.

3. **Compile and Run**:
   - Locate the `NetworkGUI` class in the `fr.univ.amu.conveyorbelts` package.
   - Run the `main` method of the `NetworkGUI` class to start the simulation and display the graphical representation of the network.

### Example Usage

The following is a basic example of how to create and display a conveyor belt network:

```java
BeltNetwork beltNetwork = new BeltNetwork();

BeltNode i1 = new BeltNode(50, 100, "i1", Color.GREEN);
// ... add more nodes and belts ...

beltNetwork.addNode(i1);
// ... add nodes and belts to the network ...

NetworkGUI gui = new NetworkGUI(beltNetwork);
gui.setVisible(true);
```

### Customizing the Network

You can easily customize the network by defining your own nodes and belts or using different suppliers:

```java
Supplier<Belt> customBeltSupplier = BeltSuppliers.constantLengthBeltSupplier(10);
Belt customBelt = customBeltSupplier.get();
// Add customBelt to your network
```

### Extending the Project

To extend the project, consider:

- Adding new types of nodes (e.g., routers, diverters).
- Implementing more sophisticated encoding strategies.
- Developing additional GUI features, such as interactive node manipulation.

## Acknowledgments

This project was developed as part of an internship in **Laboratoire d'Informatique et Systèmes** (LIS) at Luminy, Marseille, under the guidance of **Dr. Guyslain Naves**.

Special thanks to Dr. Guyslain Naves for his mentorship and guidance throughout the research internship.
