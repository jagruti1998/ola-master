package ola.application.configuration;

import ola.application.entity.OlaTermAndCondition;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class PDFGenerator {

    private static final float MARGIN = 40;
    private static final float PAGE_WIDTH = PDRectangle.A4.getWidth();
    private static final float PAGE_HEIGHT = PDRectangle.A4.getHeight();
    private static final float CONTENT_WIDTH = PAGE_WIDTH - 2 * MARGIN;
    private static final float LINE_HEIGHT = 14;
    private static final float LEADING = 16.5f;
    private static final float START_Y = PAGE_HEIGHT - MARGIN;
    private float currentY = START_Y;

    public byte[] generatePDF(List<OlaTermAndCondition> termsAndConditions) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD, 18);
            contentStream.setLeading(LEADING);
            contentStream.newLineAtOffset(MARGIN, currentY);

            addText(contentStream, "Terms & Conditions of TaxiCo Company", PDType1Font.TIMES_BOLD, 18, document);
            contentStream.newLine();
            contentStream.newLine();

            for (OlaTermAndCondition term : termsAndConditions) {
                contentStream = addSection(contentStream, "General Terms:", term.getGeneralTerms(), document);
                contentStream = addSection(contentStream, "Booking and Reservations:", term.getBookingAndReservations(), document);
                contentStream = addSection(contentStream, "Passenger Responsibilities:", term.getPassengerResponsibilities(), document);
                contentStream = addSection(contentStream, "Driver Responsibilities:", term.getDriverResponsibilities(), document);
                contentStream = addSection(contentStream, "Fares and Payments:", term.getFaresAndPayments(), document);
                contentStream = addSection(contentStream, "Cancellations and Refunds:", term.getCancellationsAndRefunds(), document);
                contentStream = addSection(contentStream, "Liability:", term.getLiability(), document);
                contentStream = addSection(contentStream, "Complaints and Disputes:", term.getComplaintsAndDisputes(), document);
                contentStream = addSection(contentStream, "Amendments:", term.getAmendments(), document);
                contentStream = addSection(contentStream, "Privacy Policy:", term.getPrivacyPolicy(), document);
                contentStream = addSection(contentStream, "Contact Information:", term.getContactInformation(), document);
            }

            contentStream.endText();
            contentStream.close();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error while generating PDF", e);
        }
    }

    private void addText(PDPageContentStream contentStream, String text, PDType1Font font, int fontSize, PDDocument document) throws IOException {
        contentStream.setFont(font, fontSize);
        for (String line : text.split("\n")) {
            contentStream = ensureNewLine(contentStream, document);
            wrapText(contentStream, line, CONTENT_WIDTH, document);
        }
    }

    private PDPageContentStream addSection(PDPageContentStream contentStream, String heading, String content, PDDocument document) throws IOException {
        addText(contentStream, heading, PDType1Font.TIMES_BOLD, 12, document);
        contentStream.newLineAtOffset(0, -LINE_HEIGHT);
        currentY -= LINE_HEIGHT;
        addText(contentStream, content, PDType1Font.TIMES_ROMAN, 12, document);
        contentStream.newLineAtOffset(0, -LINE_HEIGHT);
        currentY -= LINE_HEIGHT;

        return ensureNewLine(contentStream, document);
    }

    private PDPageContentStream ensureNewLine(PDPageContentStream contentStream, PDDocument document) throws IOException {
        if (currentY <= MARGIN) {
            contentStream.endText();
            contentStream.close();

            PDPage newPage = new PDPage(PDRectangle.A4);
            document.addPage(newPage);

            contentStream = new PDPageContentStream(document, newPage);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
            contentStream.setLeading(LEADING);
            contentStream.newLineAtOffset(MARGIN, START_Y);
            currentY = START_Y;
        }
        return contentStream;
    }

    private void wrapText(PDPageContentStream contentStream, String text, float width, PDDocument document) throws IOException {
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        for (String word : words) {
            if (getStringWidth(line + word) < width) {
                line.append(word).append(" ");
            } else {
                ensureNewLine(contentStream, document);
                contentStream.showText(line.toString().trim());
                contentStream.newLineAtOffset(0, -LINE_HEIGHT);
                currentY -= LINE_HEIGHT;
                line = new StringBuilder(word).append(" ");
            }
        }
        if (line.length() > 0) {
            ensureNewLine(contentStream, document);
            contentStream.showText(line.toString().trim());
            contentStream.newLineAtOffset(0, -LINE_HEIGHT);
            currentY -= LINE_HEIGHT;
        }
    }

    private float getStringWidth(String text) {
        return text.length() * 6; // Approximate width of a character in the default font size
    }
}
