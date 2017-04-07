package com.lihao.springboot.controllers;

import com.lihao.springboot.Responses;
import com.lihao.springboot.models.MapBackedRepository;
import com.lihao.springboot.models.Pet;
import com.lihao.springboot.models.Pets;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * @author bencong.lh
 * @version $Id: PetController, v0.1 2017年04月06日 下午8:21 bencong.lh Exp $
 */

@RestController
@RequestMapping("/api/pet")
@Api(value = "/pet", description = "Operations about pets")
public class PetController {
    PetRepository petData = new PetRepository();

    @RequestMapping(value = "/{petId}", method = GET)
    @ApiOperation(
            value = "Find pet by ID", notes = "Returns a pet when ID < 10. ID > 10 or nonintegers will simulate API " +
            "error conditions",
            response = Pet.class,
            responseHeaders = {
                    @ResponseHeader(name = "header4", response = String.class),
                    @ResponseHeader(name = "header3", response = String.class)
            },
            authorizations = {
                    @Authorization(value = "api_key"),
                    @Authorization(value = "petstore_auth", scopes = {
                            @AuthorizationScope(scope = "write:pets", description = ""),
                            @AuthorizationScope(scope = "read:pets", description = "")
                    })})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied", responseHeaders = {
                    @ResponseHeader(name = "header2", response = String.class),
                    @ResponseHeader(name = "header1", response = String.class)
            }),
            @ApiResponse(code = 404, message = "Pet not found")}
    )
    public ResponseEntity<Pet> getPetById(
            @ApiParam(value = "ID of pet that needs to be fetched", allowableValues = "range[1,5]", required = true)
            @PathVariable("petId") String petId)
            throws NotFoundException {
        Pet pet = petData.get(Long.valueOf(petId));
        if (null != pet) {
            return Responses.ok(pet);
        } else {
            throw new NotFoundException(404, "Pet not found");
        }
    }

    @RequestMapping(method = POST)
    @ApiOperation(value = "Add a new pet to the store",
            authorizations = @Authorization(value = "petstore_auth", scopes = {
                    @AuthorizationScope(scope = "write:pets", description = ""),
                    @AuthorizationScope(scope = "read:pets", description = "")
            }))
    @ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input")})
    public ResponseEntity<String> addPet(
            @ApiParam(value = "Pet object that needs to be added to the store", required = true) @RequestBody Pet pet) {
        petData.add(pet);
        return Responses.ok("SUCCESS");
    }

    @RequestMapping(method = PUT)
    @ApiOperation(value = "Update an existing pet",
            authorizations = @Authorization(value = "petstore_auth", scopes = {
                    @AuthorizationScope(scope = "write:pets", description = ""),
                    @AuthorizationScope(scope = "read:pets", description = "")
            }))
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Pet not found"),
            @ApiResponse(code = 405, message = "Validation exception")})
    public ResponseEntity<String> updatePet(
            @ApiParam(value = "Pet object that needs to be added to the store", required = true) @RequestBody Pet pet) {
        petData.add(pet);
        return Responses.ok("SUCCESS");
    }

    @RequestMapping(value = "/findByStatus", method = GET)
    @ApiOperation(
            value = "Finds Pets by status",
            notes = "Multiple status values can be provided with comma seperated strings",
            response = Pet.class,
            responseContainer = "List",
            authorizations = @Authorization(value = "petstore_auth", scopes = {
                    @AuthorizationScope(scope = "write:pets", description = ""),
                    @AuthorizationScope(scope = "read:pets", description = "")
            }))
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid status value")})
    /** TODO: This renders parameter as
     *
     "name": "status",
     "in": "query",
     "description": "Status values that need to be considered for filter",
     "required": false,
     "type": "array",
     "items": {"type": "string"},
     "collectionFormat": "multi",
     "default": "available"
     */
    public ResponseEntity<List<Pet>> findPetsByStatus(
            @ApiParam(value = "Status values that need to be considered for filter",
                    required = true,
                    defaultValue = "available",
                    allowableValues = "available,pending,sold",
                    allowMultiple = true)
            @RequestParam("status") String status) {
        return Responses.ok(petData.findPetByStatus(status));
    }

    @RequestMapping(value = "/findByTags", method = GET)
    @ApiOperation(
            value = "Finds Pets by tags",
            notes = "Muliple tags can be provided with comma seperated strings. Use tag1, tag2, tag3 for testing.",
            response = Pet.class,
            responseContainer = "List",
            authorizations = @Authorization(value = "petstore_auth", scopes = {
                    @AuthorizationScope(scope = "write:pets", description = ""),
                    @AuthorizationScope(scope = "read:pets", description = "")
            }))
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid tag value")})
    @Deprecated
    /** TODO: This renders the parameter as
     "name": "tags",
     "in": "query",
     "description": "Tags to filter by",
     "required": false,
     "type": "array",
     "items": {"type": "string"},
     "collectionFormat": "multi" */
    public ResponseEntity<List<Pet>> findPetsByTags(
            @ApiParam(
                    value = "Tags to filter by",
                    required = true,
                    allowMultiple = true)
            @RequestParam("tags") String tags) {
        return Responses.ok(petData.findPetByTags(tags));
    }

    @RequestMapping(value = "/findPetsHidden", method = GET)
    @ApiOperation(
            value = "Finds Pets (hidden)",
            notes = "Hidden method",
            response = Pet.class,
            responseContainer = "List",
            hidden = true,
            authorizations = @Authorization(value = "petstore_auth", scopes = {
                    @AuthorizationScope(scope = "write:pets", description = ""),
                    @AuthorizationScope(scope = "read:pets", description = "")
            }))
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid tag value")})
    public ResponseEntity<List<Pet>> findPetsHidden(
            @ApiParam(
                    value = "Tags to filter by",
                    required = true,
                    allowMultiple = true)
            @RequestParam("tags") String tags) {
        return Responses.ok(petData.findPetByTags(tags));
    }

    static class PetRepository extends MapBackedRepository<Long, Pet> {
        public List<Pet> findPetByStatus(String status) {
            return where(Pets.statusIs(status));
        }

        public List<Pet> findPetByTags(String tags) {
            return where(Pets.tagsContain(tags));
        }
    }
}
