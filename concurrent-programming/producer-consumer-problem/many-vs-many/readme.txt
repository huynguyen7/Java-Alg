*Producer-Consumer describes how a fixed size buffer messaging in multiprocessing communication works.
*Problem: the buffer maybe full and there is no space for generating data. Or, the buffer is empty so the client has no data to pull.
*The solution -> Create a queue that let consumer and producer notifies each other if there is any data just generated or if the queue is empty.

*Visualization:                                 
                                                +------------+
                                                |            |
                                            +-> |  CONSUMER  |
                                            |   |            |
                                            |   +------------+
                                            |
    +------------+                          |   +------------+
    |            |                          |   |            |
    |  PRODUCER  | <-+                      +-> |  CONSUMER  |
    |            |   |                      |   |            |
    +------------+   |     +----------+     |   +------------+
                     +---> |  BUFFER  | <---+   
    +------------+   |     +----------+     |   +------------+
    |            |   |                      |   |            |
    |  PRODUCER  | <-+                      +-> |  CONSUMER  |
    |            |                          |   |            |
    +------------+                          |   +------------+
                                            |
                                            |   +------------+
                                            |   |            |
                                            +-> |  CONSUMER  |
                                                |            |
                                                +------------+
