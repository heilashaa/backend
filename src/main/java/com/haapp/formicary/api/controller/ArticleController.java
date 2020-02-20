package com.haapp.formicary.api.controller;

import com.haapp.formicary.api.message.article.ArticleRequest;
import com.haapp.formicary.api.message.article.ArticleResponse;
import com.haapp.formicary.api.message.article.ArticlesResponse;
import com.haapp.formicary.domain.model.ArticleDto;
import com.haapp.formicary.domain.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Api(value = "REST API for campaigns Articles. API allows operations with article: create, read, update, delete."
        /*, authorizations = {@Authorization(BASIC_AUTH)}*/)
@RestController
@RequestMapping(value = "/api/v1/article", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ArticleController {

    private ArticleService articleService;

    @ApiOperation(value = "Select all articles"/*, authorizations = {@Authorization(BASIC_AUTH)}*/)
    @GetMapping()
    @ResponseStatus(OK)
    public ArticlesResponse getArticles() {
        List<ArticleDto> articlesDto = articleService.getAll();
        return new ArticlesResponse(articlesDto);
    }

    @ApiOperation(value = "Find article by id"/*, authorizations = {@Authorization(BASIC_AUTH)}*/)
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public ArticleResponse getArticle(
            @ApiParam(value = "Article ID", required = true)
            @PathVariable Long id) {
        ArticleDto articleDto = articleService.findById(id);
        return new ArticleResponse(articleDto);
    }

    @ApiOperation(value = "Add new article"/*, authorizations = {@Authorization(BASIC_AUTH)}*/)
    @PostMapping(value = "", consumes = {MULTIPART_FORM_DATA_VALUE/*, APPLICATION_JSON_VALUE*/})
    @ResponseStatus(CREATED)
    public ArticleResponse createArticle(
            @ApiParam(value = "Article", required = true)
            /*@RequestPart*/ @Valid ArticleRequest request,
            @ApiParam(value = "image" /*allowMultiple = true,*/)
            @RequestPart(value = "file") MultipartFile/*[]*/ image) {
        ArticleDto articleDto = request.getArticleDto();
        articleDto = articleService.create(articleDto);
        return new ArticleResponse(articleDto);
    }

//    @Transactional
//    @PreAuthorize("hasAnyRole('COMPANY.CREATE', 'COMPANY.EDIT')")
//    @RequestMapping(value="/companies/{id}", method=RequestMethod.PUT , consumes=MULTIPART_FORM_DATA)
//    public CompanyVO updateCompany(@PathVariable String id, @RequestParam(required=false, value="file") MultipartFile file,
//                                   @RequestParam("data") String companyJsonStr) throws IOException {
//        CompanyVO inputCompany = fromJsonString(companyJsonStr, CompanyVO.class);
//        Company company = findCompany(id);
//
//        setCompanyAttributes(inputCompany, company, file);
//        companyDao.save(company);
//
//        return convertToCompanyVO(company);
//    }

//    @RequestMapping(value = "/patientp", method = RequestMethod.POST,  consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
//    public ResponseEntity<?> insertPatientInfo(
//            @RequestPart PatientInfoDTO patientInfoDTO,
//            @RequestPart("file") MultipartFile file) {
//    }

//
//    @SuppressWarnings("rawtypes")
//    @RequestMapping(value = "/DataTransfer", method = RequestMethod.POST, produces = {
//            MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = {  MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE} )
//    @ApiOperation(value = "Sbm Data Transfer Service", response = Iterable.class)
//    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully find."),
//            @ApiResponse(code = 400, message = "There has been an error."),
//            @ApiResponse(code = 401, message = "You are not authorized to save the resource"),
//            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
//    ResponseEntity processDataTransfer(@RequestPart(name="file") MultipartFile  file, @RequestPart(name="param") DataTransferInputDto param);
//


//    @PostMapping(value = "/v1/catalog/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
//    public void createNewObjectWithImage(
//            @RequestPart ModelTO modelTO,
//            @RequestPart MultipartFile image)
//
//    curl -X POST "https://your-url.com/v1/catalog/create" -H  "accept: application/json;charset=UTF-8" -H  "Content-Type: multipart/form-data" -F "image=@/pathtoimage/powerRager.jpg;type=image/jpeg" -F "modelTO={\"name\":\"White\"};type=application/json;charset=utf-8"



//    @RequestMapping(path = "/{groupId}", method = RequestMethod.POST,
//            consumes = {"multipart/form-data"})
//    public ExpenseSnippetGetDto create(@ModelAttribute ExpensePostDto expenseDto, @PathVariable long groupId, Principal principal) throws IOException {
//        //...
//    }

//    @POST
//    @RequestMapping("/upload")
//    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file, @RequestParam("expenseDto") ExpensePostDto expenseDto)
//    {
//        if (file.isEmpty()) {
//            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
//        } else {
//            //...
//        }
//    }
//    uploadFile=function(fileData, otherData){
//        var formData=new FormData();
//        formData.append('file',fileData);
//        formData.append('expenseDto',otherData);
//        return $http({
//                method: 'POST',
//                url: '/api/uploadFile',
//                data: formData,
//                headers:{
//            'Content-Type':undefined,
//                    'Accept':'application/json'
//        }
//    });
//    };

    @ApiOperation(value = "Delete article by id" /*, authorizations = {@Authorization(BASIC_AUTH)}*/)
    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public ArticleResponse deleteArticle(
            @ApiParam(value = "Article ID", required = true)
            @PathVariable Long id) {
        ArticleDto articleDto = articleService.deleteById(id);
        return new ArticleResponse(articleDto);
    }

    @ApiOperation(value = "Update article"/*, authorizations = {@Authorization(BASIC_AUTH)}*/)
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ArticleResponse updateArticle(
            @ApiParam(value = "Article ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Article", required = true)
            @RequestBody @Valid ArticleRequest request) {
        ArticleDto articleDto = request.getArticleDto();
        articleDto = articleService.update(articleDto, id);
        return new ArticleResponse(articleDto);
    }
}
