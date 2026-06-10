# AI Sentinel - Unstructured Operational Risks

AI Sentinel detected operational risks that were not automatically remediated in this PR.
The agent selected one HIGH severity risk for a focused automated patch and documented the remaining risks for human review.

## Blocking async call in request-handling path

- Severity: HIGH
- File: src/main/java/com/tw/guardianAgent/ListarTarefasController.java
- Line hint: Line 42
- Reason: The use of join() on a CompletableFuture in a request-handling path can block the request thread, potentially leading to thread exhaustion and decreased system responsiveness.
- Suggested fix: Replace the blocking join() call with a non-blocking approach, such as using thenApply() or thenCompose() to handle the result of the CompletableFuture.
- Why it was not automatically structured: The risk was not selected because AI Sentinel applies only one HIGH risk remediation per run.
## Blocking async call in request-handling path

- Severity: HIGH
- File: src/main/java/com/tw/guardianAgent/ListarTarefasController.java
- Line hint: Line 57
- Reason: The diff introduces asynchronous work and then blocks the request thread waiting for completion. Under load this can exhaust request threads and degrade latency.
- Suggested fix: Add an explicit timeout and fallback before joining, or return a non-blocking type when supported.
- Why it was not automatically structured: The risk was not selected because AI Sentinel applies only one HIGH risk remediation per run.
