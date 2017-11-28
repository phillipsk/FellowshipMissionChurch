package io.techministry.android.fellowshipmissionchurch.model;

public class Chapter {

    String chapter;
    String copyright;
    ChapterParent parent;
    String label;
    String auditid;
    String osis_end;

    public Chapter(String chapter, String copyright, ChapterParent parent, String label,
        String auditid, String osis_end) {
        this.chapter = chapter;
        this.copyright = copyright;
        this.parent = parent;
        this.label = label;
        this.auditid = auditid;
        this.osis_end = osis_end;
    }

    class ChapterParent {
        Book book;

        public ChapterParent(Book book) {
            this.book = book;
        }
    }

    class Book {
        String path;
        String name;
        String id;

        public Book(String path, String name, String id) {
            this.path = path;
            this.name = name;
            this.id = id;
        }
    }


    /*
        "label": "",
        "auditid": "0",
        "osis_end": "eng-GNTD:2Tim.1.18",
        "next": {
        "chapter": {
            "path": "/chapters/eng-GNTD:2Tim.2",
                "name": "2 Timothy 2",
                "id": "eng-GNTD:2Tim.2"
        }
    },
        "id": "eng-GNTD:2Tim.1",
        "previous": {
        "chapter": {
            "path": "/chapters/eng-GNTD:1Tim.6",
                "name": "1 Timothy 6",
                "id": "eng-GNTD:1Tim.6"
        }
    }*/

}
