import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.Context
import org.eclipse.jetty.server.session.*
import java.io.File


fun main(args: Array<String>) {
    val app = Javalin.create()
            //.enableCorsForOrigin("*")
            .sessionHandler {
                fileSessionHandler()
            }

    app.routes {
        path("/test"){
            get(::getTest)
        }
        path("/login") {
            post(::login)
        }
    }

    app.start(8080)
}


private fun getTest(ctx: Context) {
    System.out.println("getTest requested")
    ctx.status(200)
    System.out.println("Hello " + ctx.sessionAttribute("username"))
    ctx.html("Hello " + ctx.sessionAttribute("username") + "\n")
}

private fun login(ctx: Context) {
    System.out.println("login requested")
    ctx.status(201)
    ctx.sessionAttribute("username", "Dave")
}


fun fileSessionHandler() = SessionHandler().apply { // create the session handler
    sessionCache = NullSessionCache(this).apply { // attach a cache to the handler
        sessionDataStore = FileSessionDataStore().apply { // attach a store to the cache
            val baseDir = File(System.getProperty("java.io.tmpdir"))
            this.storeDir = File(baseDir, "javalin-session-store").apply { mkdir() }
        }
    }
    httpOnly = true
}
