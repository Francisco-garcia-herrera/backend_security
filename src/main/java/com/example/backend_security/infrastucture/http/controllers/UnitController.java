package com.example.backend_security.infrastucture.http.controllers;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_security.domain.entities.Unit;
import com.example.backend_security.domain.helpers.PDFHelper;
import com.example.backend_security.domain.usecases.unit.CreateUnit;
import com.example.backend_security.domain.usecases.unit.DeleteUnit;
import com.example.backend_security.domain.usecases.unit.GetAllUnits;
import com.example.backend_security.infrastucture.adapter.DomainToDtoAdapter;
import com.example.backend_security.infrastucture.http.httprestentities.UnitHttpRestEntity;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Lazy
@RestController
@RequestMapping(path = "/units", consumes = { MediaType.APPLICATION_JSON_VALUE,
        MediaType.ALL_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
public class UnitController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GetAllUnits getAllUnits;

    @Autowired
    private CreateUnit createUnit;

    @Autowired
    private DeleteUnit deleteUnit;

    @Autowired
    private DomainToDtoAdapter domainToDtoAdapter;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        List<UnitHttpRestEntity> body = new ArrayList<>();
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<?> toReturn;
        List<Unit> unitDomain = new ArrayList<>();

        unitDomain = getAllUnits.get();
        body = domainToDtoAdapter.convertUnits(unitDomain);

        toReturn = new ResponseEntity<>(body, status);
        return toReturn;
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody UnitHttpRestEntity data) {
        UnitHttpRestEntity body = null;
        Unit createdUnit = null;
        HttpStatus status = HttpStatus.CREATED;
        ResponseEntity<?> toReturn;
        try {
            // Long userId = getAuthUserId();
            Unit unit = data.mapToDomain();
            createdUnit = createUnit.create(unit);
            body = domainToDtoAdapter.convertUnitReduced(createdUnit);

        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.error("Generic uncontrolled ERROR", e);
            return new ResponseEntity<>(e.getMessage(), status);
        } finally {
            toReturn = new ResponseEntity<>(body, status);
            logger.debug(". Status<" + status + ">");
        }
        return toReturn;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<?> toReturn;
        try {
            deleteUnit.delete(id);
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.error("Generic uncontrolled ERROR", e);
            return new ResponseEntity<>(e.getMessage(), status);
        } finally {
            toReturn = new ResponseEntity<>("Unit succesfully deleted", status);
            logger.debug(". Status<" + status + ">");
        }
        return toReturn;
    }

    @RequestMapping(value = "/zip", produces = "application/zip")
    public byte[] zipFiles(HttpServletResponse response) throws IOException {
        // Setting HTTP headers
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader("Content-Disposition", "attachment; filename=\"test.zip\"");

        // Creating byteArray stream, make it bufferable and passing this buffer to
        // ZipOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);

        // Simple file list, just for tests
        String EXPORT_DIRECTORY = "C:\\ZZ";
        Path filePath = Paths.get(EXPORT_DIRECTORY, "test.txt");
        Path exportedFilePath = Files.write(filePath, "TEXTODENTRO".getBytes(), StandardOpenOption.CREATE);
        ArrayList<File> files = new ArrayList<>(2);
        File newFile = new File(exportedFilePath.toString());
        files.add(newFile);

        // Packing files
        for (File file : files) {
            // New zip entry and copying InputStream with file to ZipOutputStream, after all
            // closing streams
            zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
            FileInputStream fileInputStream = new FileInputStream(file);

            IOUtils.copy(fileInputStream, zipOutputStream);

            fileInputStream.close();
            zipOutputStream.closeEntry();
        }

        if (zipOutputStream != null) {
            zipOutputStream.finish();
            zipOutputStream.flush();
            IOUtils.closeQuietly(zipOutputStream);
        }
        IOUtils.closeQuietly(bufferedOutputStream);
        IOUtils.closeQuietly(byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }

    @GetMapping("/get-pdf")
    public void getPdf() throws IOException {
        String html = "<h1>hello</h1>";
        String xhtml = PDFHelper.htmlToXhtml(html);
        PDFHelper.xhtmlToPdf(xhtml, "src/main/resources/template/output.pdf");
    }

}
