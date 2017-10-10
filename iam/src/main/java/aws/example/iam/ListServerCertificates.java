/*
 * Copyright 2010-2017 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package aws.example.iam;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.ListServerCertificatesRequest;
import com.amazonaws.services.identitymanagement.model.ListServerCertificatesResult;
import com.amazonaws.services.identitymanagement.model.ServerCertificateMetadata;

/**
 * Lists all server certificates associated with an AWS account
 */
public class ListServerCertificates {
    public static void main(String[] args) {

        final AmazonIdentityManagement iam =
            AmazonIdentityManagementClientBuilder.defaultClient();

        boolean done = false;

        while(!done) {
            ListServerCertificatesRequest request =
                new ListServerCertificatesRequest();

            ListServerCertificatesResult response =
                iam.listServerCertificates(request);

            for(ServerCertificateMetadata metadata :
                    response.getServerCertificateMetadataList()) {
                System.out.printf("Retrieved server certificate %s",
                        metadata.getServerCertificateName());
            }

            request.setMarker(response.getMarker());

            if(!response.getIsTruncated()) {
                done = true;
            }
        }
    }
}

