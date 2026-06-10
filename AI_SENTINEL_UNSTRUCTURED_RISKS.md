# AI Sentinel - Unstructured Operational Risks

AI Sentinel detected operational risks that were not automatically remediated in this PR.
The agent selected one HIGH severity risk for a focused automated patch and documented the remaining risks for human review.

## Long-running blocking operation in request-handling path

- Severity: HIGH
- File: src/main/java/com/tw/guardianAgent/ListarTarefasController.java
- Line hint: Line 50
- Reason: The diff introduces an explicit blocking delay in a request path. This increases latency directly and can reduce throughput by tying up request threads.
- Suggested fix: Keep the simulated external call behind an explicit timeout and fallback, or move it to a non-blocking boundary.
- Why it was not automatically structured: The risk was not selected because AI Sentinel applies only one HIGH risk remediation per run.
