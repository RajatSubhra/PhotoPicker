package io.rajat.rsc.photopicker.models;



public class MetaDataAndSimilarImages {

    public ImageResponse imageResponse;
    public MetadataResponse metadataResponse;

    public MetaDataAndSimilarImages(ImageResponse imageResponse, MetadataResponse metadataResponse) {
        this.imageResponse = imageResponse;
        this.metadataResponse = metadataResponse;
    }
}

