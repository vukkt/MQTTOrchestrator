# MQTTOrchestrator

**MQTTOrchestrator** is a modular orchestration system built on top of MQTT. It dynamically manages access control, message routing, and user/group/role configuration through MQTT control topics and a RESTful interface.

## Repository Structure

This monorepo includes two core modules:

### `messagecommander/`
Core orchestration logic responsible for:

- Managing clients, roles, groups, and ACLs
- Publishing control commands to the MQTT broker via `$CONTROL/dynamic-security/write`
- Exposing a REST API for automation and integration

### `mqtt-rbac-docker-init/`
Docker-based initialization for:

- RBAC-secured Mosquitto broker setup
- Dynamic Security plugin bootstrapping
- Cedalo Management Center integration

## API Testing

A [Postman-compatible JSON collection](https://raw.githubusercontent.com/vukkt/MQTTOrchestrator/main/MQTTOrchestrator.postman_collection.json) is included in the root of the repo for testing REST endpoints.

## Quick Start

To build and launch the full system:

```bash
docker compose up --build -d
