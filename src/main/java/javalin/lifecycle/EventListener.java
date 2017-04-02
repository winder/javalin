// Javalin - https://javalin.io
// Copyright 2017 David Åse
// Licensed under Apache 2.0: https://github.com/tipsy/javalin/blob/master/LICENSE

package javalin.lifecycle;

@FunctionalInterface
public interface EventListener {
    void handleEvent(Event e);
}
