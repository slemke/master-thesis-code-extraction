import io.github.slemke.methodextractor.exceptions.FileException;
import io.github.slemke.methodextractor.extraction.MethodNameExtractor;
import io.github.slemke.methodextractor.files.DirectoryReader;
import io.github.slemke.methodextractor.files.FileReader;
import io.github.slemke.methodextractor.files.FileWriter;

import java.util.ArrayList;
import java.util.Collections;

public class NameCollector {

    public static String[] verbs() {
        return new String[]{
                "wrap",
                "delegate",
                "map",
                "run",
                "notify",
                "complete",
                "handle",
                "execute",
                "read",
                "setup",
                "register",
                "init",
                "configure",
                "validate",
                "write",
                "get",
                "set",
                "do",
                "transport",
                "use",
                "parse",
                "check",
                "explain",
                "find",
                "cluster",
                "is",
                "build",
                "remove",
                "verify",
                "command",
                "persist",
                "log",
                "route",
                "create",
                "wait",
                "replace",
                "analyze",
                "calculate",
                "evaluate",
                "clone",
                "store",
                "search",
                "refresh",
                "rewrite",
                "query",
                "advance",
                "reset",
                "assert",
                "close",
                "await",
                "cancel",
                "start",
                "apply",
                "retry",
                "finish",
                "create",
                "process",
                "mark",
                "perform",
                "fetch",
                "has",
                "count",
                "move",
                "send",
                "fork",
                "try",
                "reduce",
                "make",
                "fill",
                "consume",
                "guess",
                "scroll",
                "allow",
                "slice",
                "clear",
                "suggest",
                "compare",
                "extract",
                "terminate",
                "collect",
                "reset",
                "skip",
                "should",
                "resolve",
                "split",
                "merge",
                "apply",
                "convert",
                "expand",
                "declare",
                "finish",
                "check",
                "compute",
                "block",
                "respond",
                "retry",
                "update",
                "select",
                "add",
                "delete",
                "extract",
                "prepare",
                "save",
                "load",
                "initialize",
                "stop",
                "close",
                "limit",
                "spawn",
                "display",
                "prepare",
                "clear",
                "filter",
                "clean",
                "put",
                "recover",
                "refresh",
                "cluster",
                "list",
                "generate",
                "join",
                "contains",
                "ensure",
                "send",
                "elect",
                "target",
                "serialize",
                "truncate",
                "adjust",
                "match",
                "accept",
                "duplicate",
                "throw",
                "format",
                "visit",
        };
    }

    public static String[] purge() {
        return new String[]{
                "index",
                "not",
                "origin",
                "for",
                "identical",
                "analyzer",
                "and",
                "returns",
                "any",
                "text",
                "detail",
                "aliases",
                "mapping",
                "types",
                "rest",
                "default",
                "local",
                "names",
                "error",
                "cannot",
                "node",
                "old",
                "shard",
                "timed",
                "time",
                "hits",
                "profile",
                "maybe",
                "strict",
                "decPending",
                "global",
                "routed",
                "terms",
                "ids",
                "postings",
                "next",
                "positions",
                "payloads",
                "timestamp",
                "action",
                "script",
                "upsert",
                "always",
                "java",
                "jvm",
                "keys",
                "elements",
                "Virtual",
                "definitely",
                "self",
                "admin",
                "bulk",
                "term",
                "multi",
                "field",
                "state",
                "node",
                "allocation",
                "snapshot",
                "listed",
                "levels",
                "global",
                "custom",
                "minimum",
                "post",
                "first",
                "last",
                "unassigned",
                "indices",
                "level",
                "status",
                "settings",
                "discovery",
                "message",
                "task",
                "must",
                "routing",
                "repository",
                "value",
                "iterator",
                "meta",
                "content",
                "context",
                "exists",
                "parser",
                "concrete",
                "attributes",
                "char",
                "simple",
                "inner",
                "features",
                "human",
                "probably",
                "full",
                "source",
                "active",
                "flags",
                "docs",
                "warmer",
                "completion",
                "translog",
                "version",
                "primary",
                "filtering",
                "exponential",
                "constant",
                "supports",
                "number",
                "item",
                "need",
                "opType",
                "forced",
                "parent",
                "async",
                "non",
                "realtime",
                "inner",
                "when",
                "and",
                "internal",
                "enough",
                "lenient",
                "ackTimeout",
                "concrete",
                "routing",
                "full",
                "size",
                "impacts",
                "docID",
                "less",
                "more",
                "halt",
                "implies",
                "date",
                "flavor",
                "health",
                "stats",
                "uuid",
                "coordination",
                "can",
                "running",
                "leader",
                "role",
                "threshold",
                "stay",
                "cannot",
                "decisions",
                "distinct",
                "all",
                "assigned",
                "relocating",
                "with",
                "length",
                "missing",
                "recursive",
                "graceful",
                "array",
                "might",
                "immutable",
                "patterns",
                "diversified",
                "member",
                "counter",
                "sub",
                "max",
                "shared",
                "flattening",
                "bounds",
                "plus",
                "minus",
                "higher",
                "server",
                "template",
                "double",
                "docs",
                "previous",
                "unit",
                "seqNo"
        };
    }

    public static void main(String[] args) {
        MethodNameExtractor extractor = new MethodNameExtractor();

        String[] projects = {
            // path of projects
        };

        String[] verbs = NameCollector.verbs();
        String[] purge = NameCollector.purge();
        try {
            ArrayList<String> goodNames = new ArrayList<>();
            ArrayList<String> badNames = new ArrayList<>();
            ArrayList<String> files = new ArrayList<>();
            for(String path : projects) {
                files.addAll(DirectoryReader.read(path));
            }
            System.out.println(files.size());
            for(String file : files) {
                final ArrayList<String> extractedFeatures = extractor.extract(FileReader.read(file));
                for(String feature : extractedFeatures) {

                    final boolean upperCase = Character.isUpperCase(feature.charAt(0));
                    if(upperCase) {
                        if(!badNames.contains(feature))
                            badNames.add(feature);
                        continue;
                    }

                    boolean yes = false;
                    for (String s : verbs) {
                        if(feature.startsWith(s)) {
                            yes = true;
                        }
                    }
                    if(yes) {
                        if(!goodNames.contains(feature))
                            goodNames.add(feature);
                        continue;
                    }

                    boolean yesPurge = false;
                    for(String s : purge) {
                        if(feature.startsWith(s)) {
                            yesPurge = true;
                        }
                    }
                    if(yesPurge) continue;

                    if(feature.startsWith("to")) {
                        if(!badNames.contains(feature))
                            badNames.add(feature);
                        continue;
                    }
                    if(feature.startsWith("on")) {
                        if(!badNames.contains(feature))
                            badNames.add(feature);
                        continue;
                    }


                    if(feature.startsWith("equal")) {
                        if(!badNames.contains(feature))
                            badNames.add(feature);
                        continue;
                    }

                    if(feature.startsWith("from")) {
                        if(!badNames.contains(feature))
                            badNames.add(feature);
                        continue;
                    }

                    if(feature.startsWith("hashcode")) {
                        if(!badNames.contains(feature))
                            badNames.add(feature);
                        continue;
                    }

                    if(feature.length() < 3) {
                        if(!badNames.contains(feature))
                            badNames.add(feature);
                        continue;
                    }

                    if(feature.startsWith("new")) {
                        if(!badNames.contains(feature))
                            badNames.add(feature);
                        continue;
                    }

                    if(feature.chars().allMatch(Character::isLetter)) {
                        if(!goodNames.contains(feature))
                            goodNames.add(feature);
                        continue;
                    }

                    if(!badNames.contains(feature))
                        badNames.add(feature);
                }
            }

            Collections.shuffle(goodNames);
            Collections.shuffle(badNames);

            System.out.println(goodNames.size());
            System.out.println(badNames.size());


            for (String a : goodNames) {
                FileWriter.appendToFile(a + "\n", "src/main/resources/generated_clean.txt");
            }
            for (String b : badNames) {
                FileWriter.appendToFile(b + "\n", "src/main/resources/generated_not_clean.txt");
            }
        } catch (FileException e) {
            e.printStackTrace();
        }
    }
}
