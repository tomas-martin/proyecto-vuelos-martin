package com.example.ProyectoFinalMartin.config;

import com.example.ProyectoFinalMartin.model.audit.Revision;
import org.hibernate.envers.RevisionListener;

public class CustomRevisionListener implements RevisionListener {
    public void newRevision(Object revisionEntity) { final Revision revision = (Revision) revisionEntity;}
}
