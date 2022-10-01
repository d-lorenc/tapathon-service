package com.example.customerprofile;

import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.ImageNameSubstitutor;

public class CorporateHarborImageNameSubstitutor extends ImageNameSubstitutor {

    @Override
    public DockerImageName apply(DockerImageName original) {
        var originalName = original.asCanonicalNameString();
        System.out.println(">>>>>" + originalName);
        var substitutedName = "harbor-repo.vmware.com/dockerhub-proxy-cache/" + originalName;
        if (originalName.contains("postgres")) {
            substitutedName = "harbor-repo.vmware.com/dockerhub-proxy-cache/library/" + originalName;
        }
        System.out.println(substitutedName);

        System.out.println(">>>>>" + substitutedName);
        return DockerImageName.parse(substitutedName);
    }

    @Override
    protected String getDescription() {
        return "Corporate Harbor Image Name Substitutor";
    }
}
