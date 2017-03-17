package Repository;

import Domain.Movie;
import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter;

import javax.xml.stream.*;
import java.io.*;


/**
 * Created by Sergiu on 12/15/2016.
 */
public class MovieXMLRepository extends RepositoryInMemory<Movie, Integer> {
    String fileName;

    public MovieXMLRepository(String fileName) {
        this.fileName = fileName;
        readFile();

    }

    private void readFile() {
        try(InputStream inputStream = new FileInputStream(fileName)) {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);
            readFromXML(reader);

        }catch(IOException | XMLStreamException e)
            {
                e.printStackTrace();
            }
    }

    private void readFromXML(XMLStreamReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    if(reader.getLocalName().equals("Movie")) {
                        Movie f = readFilm(reader);
                        super.save(f);
                    }
            }
        }
    }

    private Movie readFilm(XMLStreamReader reader) throws XMLStreamException {
        Movie f = new Movie(1,"","",1);
        try {
            f.setId(Integer.parseInt(reader.getAttributeValue(null, "id")));
            f.setTitle(reader.getAttributeValue(null, "title"));
            f.setType(reader.getAttributeValue(null, "type"));
            f.setYear(Integer.parseInt(reader.getAttributeValue(null, "year")));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return f;
    }

    private void writeFile() {
        try(FileOutputStream output = new FileOutputStream(fileName)) {
            XMLOutputFactory outputFactory = XMLOutputFactory.newFactory();
            XMLStreamWriter wr = outputFactory.createXMLStreamWriter(output);
            wr = new IndentingXMLStreamWriter(wr);

            writeToXMLFile(wr);
        } catch (IOException e){

        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }
    }

    private void writeToXMLFile(XMLStreamWriter wr) throws XMLStreamException {
        wr.writeStartDocument();
        wr.writeStartElement("Movies");
        super.getAll().forEach(x -> {
            try {
                writeFilm(x, wr);
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        });
        wr.writeEndElement();
        wr.writeEndDocument();
    }

    private void writeFilm(Movie x, XMLStreamWriter wr) throws XMLStreamException {
        wr.writeStartElement("Movie");
        wr.writeAttribute("id",x.getId().toString());
        wr.writeAttribute("title", x.getTitle());
        wr.writeAttribute("type", x.getType());
        wr.writeAttribute("year", x.getYear().toString());
        wr.writeEndElement();
    }

    @Override
    public void save(Movie entity){
        super.save(entity);
        writeFile();
    }

    @Override
    public void delete(Integer id_)
    {
        super.delete(id_);
        writeFile();
    }

    @Override
    public void update(Integer id_, Movie f1)
    {
        super.update(id_, f1);
        writeFile();
    }

    @Override
    public Movie find(Integer id_)
    {
        return super.find(id_);
    }

}