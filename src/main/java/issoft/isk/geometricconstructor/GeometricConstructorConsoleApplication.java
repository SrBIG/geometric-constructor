package issoft.isk.geometricconstructor;

import issoft.isk.geometricconstructor.model.converter.PictureConverter;
import issoft.isk.geometricconstructor.model.dto.FigureDTO;
import issoft.isk.geometricconstructor.model.dto.FigurePropertyDTO;
import issoft.isk.geometricconstructor.model.dto.GroupDTO;
import issoft.isk.geometricconstructor.model.dto.PictureDTO;
import issoft.isk.geometricconstructor.service.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
@SpringBootApplication
public class GeometricConstructorConsoleApplication implements CommandLineRunner {
    private static final String SQUARE = "square";
    private static final String TRIANGLE = "triangle";
    private static final String CIRCLE = "circle";

    private static final String COLOR = "color";
    private static final String BORDER = "border";
    private static final String CONTENT = "content";

    @Autowired
    final private PictureService pictureService;
    @Autowired
    final private PictureConverter pictureConverter;

    public static void main(String[] args) {
        SpringApplication.run(GeometricConstructorConsoleApplication.class, args);
    }

    @Override
    public void run(String... args) {

        var blueTriangle = createFigureDTO(TRIANGLE, COLOR, "#007CCE");
        var greenTriangle = createFigureDTO(TRIANGLE, COLOR, "#6B8E23");

        var circle = createFigureDTO(CIRCLE, null, null);
        var dottedBorderCircle = createFigureDTO(CIRCLE, BORDER, "dotted");

        var squareB = createFigureDTO(SQUARE, CONTENT, "B");
        var squareK = createFigureDTO(SQUARE, CONTENT, "K");

        var group = new GroupDTO();
        group.setDisplayMethod("HORIZONTALLY");
        Map<Integer, FigureDTO> figureMap = Map.of(1, dottedBorderCircle, 2, squareB, 3, greenTriangle);
        group.setFigures(figureMap);

        var mainGroup = new GroupDTO();
        mainGroup.setDisplayMethod("VERTICALLY");
        Map<Integer, FigureDTO> mainGroupFigureMap = Map.of(1, blueTriangle, 2, circle, 4, squareK);
        Map<Integer, GroupDTO> mainGroupGroupMap = Map.of(3, group);
        mainGroup.setFigures(mainGroupFigureMap);
        mainGroup.setGroups(mainGroupGroupMap);

        var pictureDTO = new PictureDTO();
        pictureDTO.setMainGroup(mainGroup);
        pictureDTO.setName("OOp");

        var picture = pictureConverter.toEntity(pictureDTO);

        pictureService.save(picture);
    }

    private FigureDTO createFigureDTO(String figureType, String propertyName, String propertyValue) {
        requireNonNull(figureType);

        var figureDTO = new FigureDTO();
        figureDTO.setType(figureType);

        if (nonNull(propertyName) && nonNull(propertyValue)){
            var propertyDto = new FigurePropertyDTO(propertyName, propertyValue);
            figureDTO.setProperty(propertyDto);
        }

        return figureDTO;
    }
}