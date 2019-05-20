# javalin-session-issue
Trying to reproduce https://github.com/tipsy/javalin/issues/554

To run:
    
    ./gradlew run
    Starting a Gradle Daemon, 1 busy Daemon could not be reused, use --status for details
    
    > Task :run
    [main] INFO org.eclipse.jetty.util.log - Logging initialized @646ms to org.eclipse.jetty.util.log.Slf4jLog
    [main] INFO io.javalin.Javalin -
               __                      __ _
              / /____ _ _   __ ____ _ / /(_)____
         __  / // __ `/| | / // __ `// // // __ \
        / /_/ // /_/ / | |/ // /_/ // // // / / /
        \____/ \__,_/  |___/ \__,_//_//_//_/ /_/
    
            https://javalin.io/documentation
    
    [main] INFO io.javalin.Javalin - Starting Javalin ...
    [main] INFO io.javalin.Javalin - Listening on http://localhost:8080/
    [main] INFO io.javalin.Javalin - Javalin started in 550ms \o/

To reproduce the behavior:

    $ curl -X POST -i http://localhost:8080/login                 
    HTTP/1.1 201 Created
    Date: Mon, 20 May 2019 12:26:52 GMT
    Server: Javalin
    Content-Type: text/plain
    Set-Cookie: JSESSIONID=node0a7hcpncqe4ia16sapi1lepx1s1.node0;Path=/;HttpOnly
    Expires: Thu, 01 Jan 1970 00:00:00 GMT
    Content-Length: 0
